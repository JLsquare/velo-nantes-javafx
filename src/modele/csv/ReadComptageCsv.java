package modele.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import modele.dao.ComptageDao;
import modele.dao.CompteurDao;
import modele.dao.DateInfoDao;
import modele.entities.Comptage;
import modele.entities.Compteur;
import modele.entities.DateInfo;
import modele.entities.PresenceAnomalie;

/**
 * Class ReadComptageCsv to read a csv file and add the Comptage to the database
 * @author Groupe 4B2
 */
public class ReadComptageCsv {
    DateInfoDao dateInfoDao;
    CompteurDao compteurDao;
    ComptageDao comptageDao;

    /**
     * Constructor of ReadComptageCsv
     * @param dateInfoDao the DateInfoDao to use
     * @param compteurDao the CompteurDao to use
     * @param comptageDao the ComptageDao to use
     * @throws IllegalArgumentException if dateInfoDao, compteurDao or comptageDao is null
     */
    public ReadComptageCsv(DateInfoDao dateInfoDao, CompteurDao compteurDao, ComptageDao comptageDao) throws IllegalArgumentException {
        this.dateInfoDao = dateInfoDao;
        this.compteurDao = compteurDao;
        this.comptageDao = comptageDao;
    }

    /**
     * Read a csv file and add the Comptage to the database
     * @param file the csv file to read
     * @throws IOException if the file is not found
     */
    public void read(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while(line != null){
            this.lineToComptage(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    /**
     * Convert a line of the csv file to a Comptage and add it to the database
     * @param line the line to convert
     */
    public void lineToComptage(String line){
        String[] splitLine = line.split(";");
        String date = splitLine[2];
        String idCompteur = splitLine[0];
        String anomalie = splitLine[28];
        int[] passages = new int[24];
        for(int i = 0; i < 24; i++){
            try{
                passages[i] = Integer.parseInt(splitLine[3+i]);
            }catch(NumberFormatException e){
                passages[i] = 0;
            }
        }
        DateInfo dateInfo = this.dateInfoDao.get(Date.valueOf(date));
        Compteur compteur = this.compteurDao.get(Integer.parseInt(idCompteur));
        PresenceAnomalie presenceAnomalie;
        if(anomalie.equals("")){
            presenceAnomalie = PresenceAnomalie.Nulle;
        } else {
            presenceAnomalie = PresenceAnomalie.valueOf(anomalie);
        }
        Comptage comptage = new Comptage(passages, presenceAnomalie, compteur, dateInfo);
        try{
            this.comptageDao.add(comptage);
        }catch(SQLException e){
            // Doublons
        }
    }
}
