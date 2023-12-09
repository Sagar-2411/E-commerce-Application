package com.BikkadIt.ElectronicStoreNew.exception;

public class BadApiRequest extends RuntimeException{


public BadApiRequest(String messege){

    super(messege);
}


public BadApiRequest(){
    super("Bad Request");
}

}
