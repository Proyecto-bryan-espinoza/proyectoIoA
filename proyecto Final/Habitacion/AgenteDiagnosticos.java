/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habitacion;


import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author user
 */

public class AgenteDiagnosticos extends Agent {
    
    long tini;
    int[] comp = new int[2];
    
    protected void setup() {
        tini = System.currentTimeMillis();
        addBehaviour(new miTicker(this, 2000));
    }
    
    private class miTicker extends TickerBehaviour {
        
        int minticks;
        
        public miTicker(Agent a, long intervalo) {
            super(a, intervalo);
            minticks = 0;
        }
        
        public void reset() {
            super.reset();
            //minticks = 0;
            //System.out.println("reseteo!");
        }
        
        protected void onTick() {
            long tfin = System.currentTimeMillis() - tini;
            int nticks = getTickCount(); 
            minticks++;
            
            String estado = "";
            block();
            ACLMessage msm = receive();
           
            if (msm != null) {
                //variables a valorar;
                if (msm.getSender().getLocalName().equals("temp")) {
                    comp[0] = Integer.parseInt(msm.getContent());
                }
                if (msm.getSender().getLocalName().equals("cardio")) {
                    comp[1] = Integer.parseInt(msm.getContent());
                }

                //Temperatura baja y frecuencia cardiaca anormal
                if ((comp[0] >= 35 && comp[0] < 33) && (comp[1] >= 120 && comp[1] < 86)) {
                    estado = "Temperatura baja y frecuencia cardiaca anormal";
                } 
                //Temperatura normal y frecuencia cardiaca normal
                else if ((comp[0] >= 37 && comp[0] < 36) && (comp[1] >= 84 && comp[1] < 70)) {
                    estado = "Temperatura normal y frecuencia cardiaca normal";
         
                } //Temperatura alto y frecuencia cardiaca buena
                else if ((comp[0] >= 40 && comp[0] < 39) && (comp[1] >= 68 && comp[1] < 62)) {
                    estado = "Temperatura levemente alta y frecuencia cardiaca levemente buena";
                } 
                } //Temperatura muy alto y frecuencia cardiaca excelente
                else if ((comp[0] >=42 && comp[0] < 41) && (comp[1] >= 60 && comp[1] < 50)) {
                     estado = "Temperatura muy alta y frecuencia cardiaca muy buena";
            }
            
            if (nticks == 3) {
                if (!estado.equals("")) {
                    System.out.println("----------------------------------------");
                    System.out.println("Temperatura: " + comp[0] +"C°"+ "   "
                            + "Cardio: " + comp[1]);
                    System.out.println("-->El diagnostico del paciente es " + estado);
                    estado = "";
                }
                reset();
            } 
        }
    }
}
