package com.openclassroom.mediscreenclient.model;

public enum Diabetes {



     NONE ("none"), BORDERLINE ("Borderline"), INDANGER("In danger"), EARLYONSET("Early onset");

     private final String diagnostic;

     Diabetes(String diagnostic) {
          this.diagnostic = diagnostic;
     }

     String getDiagnostic(){
          return diagnostic;
     }
}
