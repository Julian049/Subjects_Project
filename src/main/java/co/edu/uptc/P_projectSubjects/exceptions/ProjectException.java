package co.edu.uptc.P_projectSubjects.exceptions;

public class ProjectException extends Exception{
    private TypeMessage typeMessage;

    public ProjectException(TypeMessage typeMessage) {
        super(typeMessage.message);
        this.typeMessage = typeMessage;
    }


    public Message getMenssage(){
        return new Message(this.typeMessage.code,
                this.typeMessage.message,
                this.typeMessage.codeHttp);

    }
}
