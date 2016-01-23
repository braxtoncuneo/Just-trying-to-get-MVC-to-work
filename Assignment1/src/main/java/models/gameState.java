package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Braxton on 1/20/2016.
 */
public class gameState implements Serializable{

    public java.util.List<String> deck;
    public java.util.List<String> discard;

    public java.util.List<String> col1;
    public java.util.List<String> col2;
    public java.util.List<String> col3;
    public java.util.List<String> col4;

    public String action;
    public String item1;
    public String item2;
    public String item3;



    public java.util.List<String> grabCol(String column){
        if(column.contentEquals("1")){
            return col1;
        }
        if(column.contentEquals("2")){
            return col2;
        }
        if(column.contentEquals("3")){
            return col3;
        }
        if(column.contentEquals("4")){
            return col4;
        }
        return null;
    }

    public char getSuit(String card){
        if(card.length()==2){
            return card.charAt(0);
        }
        else{
            return (char) -1;
        }
    }

    public char getVal(String card){
        if(card.length()==2){
            if(card.charAt(1)>='1' && card.charAt(1)<='9'){
                return (char)(card.charAt(0)-'0');
            }
            else if(card.charAt(1)=='J'){
                return 10;
            }
            else if(card.charAt(1)=='Q'){
                return 11;
            }
            else if(card.charAt(1)=='K') {
                return 12;
            }
            else if (card.charAt(1)=='A'){
                return 13;
            }
            else{
                return 0;
            }
        }
        else{
            return (char) -1;
        }
    }

    public void newDeck(){

        Random rand= new Random();

        int i=0;
        int k;
        String curCard;
        char suit='H';
        char rank;
        while(i<4){
            if(i==1)
                suit='D';
            if(i==2)
                suit='C';
            if(i==3)
                suit='S';

            k=0;
            rank=1;
            while(k<13) {
                curCard=new String();
                curCard+=suit;
                curCard+=rank;
                deck.add(rand.nextInt()%deck.size(),curCard);
                k++;
            }
            i++;
        }

    }

    public void newGame(){

        col1.clear();
        col2.clear();
        col3.clear();
        col4.clear();
        discard.clear();
        deck.clear();
        newDeck();

    }

    public boolean deal(){

        if(deck.isEmpty()){
            return false;
        }

        int last=deck.size()-1;
        col1.add(deck.get(last));
        deck.remove(last--);
        col2.add(deck.get(last));
        deck.remove(last--);
        col3.add(deck.get(last));
        deck.remove(last--);
        col4.add(deck.get(last));
        deck.remove(last);

        return true;

    }

    public boolean discardableWith(java.util.List<String> ColA, java.util.List<String> ColB){

        if(getSuit(ColB.get(ColB.size()-1))==getSuit(ColA.get(ColA.size()-1)) &&
                getVal(ColB.get(ColB.size()-1))>getVal(ColA.get(ColA.size()-1))){
            return true;
        }

        return false;

    }

    public boolean discard(String col){

        java.util.List<String> Col=grabCol(col);

        if(discardableWith(Col,col1)){
            Col.remove(Col.size()-1);
            return true;
        }
        if(discardableWith(Col,col2)){
            Col.remove(Col.size()-1);
            return true;
        }
        if(discardableWith(Col,col3)){
            Col.remove(Col.size()-1);
            return true;
        }
        if(discardableWith(Col,col4)){
            Col.remove(Col.size()-1);
            return true;
        }

        return false;
    }

    public boolean move(String fromCol, String toCol){

        java.util.List<String> ToCol=grabCol(toCol);
        java.util.List<String> FromCol=grabCol(fromCol);

        if(ToCol.isEmpty() && ! FromCol.isEmpty()){
            ToCol.add(FromCol.get(FromCol.size()-1));
            FromCol.remove(FromCol.size()-1);
            return true;
        }

        return false;
    }


    public gameState (){

        deck= new ArrayList<String>();
        discard= new ArrayList<String>();
        col1= new ArrayList<String>();
        col2= new ArrayList<String>();
        col3= new ArrayList<String>();
        col4= new ArrayList<String>();

        action = new String ();
        item1 = new String ();
        item2 = new String ();
        item3 = new String ();

        deal();


        newDeck();

    }

}
