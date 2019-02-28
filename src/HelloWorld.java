import javax.sound.midi.VoiceStatus;
import java.text.*;
import java.util.*;

public class HelloWorld {
    public static void main(String[] args){
        try {
            HelloWorld helloWorld = new HelloWorld();
            helloWorld.methodA(1);
        }
        catch (SpecialException e){

        }
    }

    public void methodA(int money) throws SpecialException{
        if (--money <= 0) throw new SpecialException("out");
        System.out.println("methodA");
    }
}