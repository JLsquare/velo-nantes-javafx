package modele;

public class NoDatabaseException extends Exception {
    public NoDatabaseException(String message){
        super(message);
    }
}