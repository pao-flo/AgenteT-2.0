package frc.robot.subsystems;
/*import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class Leds {
    private AddressableLED mLeds;
	private AddressableLEDBuffer mLedBuffer;
	
    public Leds() {
        mLeds = new AddressableLED(5); // se establece el puerto de la RoborIO(pwm output)
        mLedBuffer = new AddressableLEDBuffer(9); // se establece el número se LEDs individuales
		mLeds.setLength(mLedBuffer.getLength());
    }


    public void setColor(){ //funcion para pintar un color, una seccion
        mLedBuffer.setRGB(0, 0, 255, 255);
		mLeds.setData(mLedBuffer);
		mLeds.start();
	}
    public void setColorDisabled(){ //funcion para pintar un color, una seccion
        mLedBuffer.setRGB(0, 0, 0, 255);
		mLeds.setData(mLedBuffer);
		mLeds.start();
	}

}*/


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.Constants;

/*
* Libreria para controlar la tira de leds rgb
*/

public class Leds {

	public enum Colors{
		Red,
		Yellow,
		Green,
		Aqua,
		Blue,
		Magenta,
		White,
		Purple,
		Black
	}

	public enum Section{
		All
	}

	public enum State{
		Disable,
		Auto,
		Teleop,
		TeleopFull,
		AutoRunning,
		AutoEnd,
		Aligning,
		Aligned,
		NotAutoAign,

		ManualControl,
		Aiming,
		PreparingToShoot,
		ReadyToShoot,
		IsClimbing,
		ClimbComplete
	}

    private static Leds mInstance;  //instancia unica 

    public synchronized static Leds getInstance() { //para leer la instancia unica 
        if (mInstance == null) {
            mInstance = new Leds();
        }
        return mInstance;
	}
	
	private AddressableLED mLeds;
	private AddressableLEDBuffer mLedBuffer;
	private Colors cacheColorCenter;
	private Colors cacheColorEdges;
	

    public Leds() {
        mLeds = new AddressableLED(5); // se establece el puerto de la RoborIO(pwm output)
        mLedBuffer = new AddressableLEDBuffer(Constants.kLedsAllLedsSize); // se establece el número se LEDs individuales
		mLeds.setLength(mLedBuffer.getLength());
		cacheColorCenter = Colors.Black;
		cacheColorEdges = Colors.Black;
		TurnOff();
    }


    public void SetState(State ledState){ //para fijar los estados con los leds
		switch(ledState){
			case Disable:
				setColor(Section.All,Colors.Blue);
				break;
			case ManualControl:
				setColor(Section.All, Colors.Aqua);
				break;
			case Aiming:
				setColor(Section.All, Colors.Aqua);
				break;
			case PreparingToShoot:
				setColor(Section.All, Colors.Blue);
				break;
			case ReadyToShoot:
				setColor(Section.All, Colors.Green);
				break;
			case IsClimbing:
				setColor(Section.All, Colors.Magenta);
				break;
			case ClimbComplete:
				setColor(Section.All, Colors.White);
				break;
		}
	}

	private void setColor(Section sectionx, Colors colorx){ //funcion para pintar un color, una seccion
		boolean needChange = true;
		if(needChange){
			int[] colorvals = GetColor(colorx);
			switch(sectionx){
				case All:
					for (var i = 0; i < Constants.kLedsAllLedsSize; i++) {
						mLedBuffer.setRGB(i,colorvals[0],colorvals[1],colorvals[2]);
					}
					break;
			}
			mLeds.setData(mLedBuffer);
			mLeds.start();
		}
    }

    public void TurnOff(){ //para apagar todos los leds
        setColor(Section.All,Colors.Black);
        mLeds.stop();
	}
	
	private int[] GetColor(Colors colorx){  //funcion para regresar un color
		int[] colorvals = new int[]{0,0,0};
		switch(colorx){
			case Red:
				colorvals = new int[]{255,0,0};
				break;
			case Yellow:
				colorvals = new int[]{255,255,0};
				break;
			case Green:
				colorvals = new int[]{0,255,0};
				break;
			case Aqua:
				colorvals = new int[]{0,255,255};
				break;
			case Blue:
				colorvals = new int[]{0,0,255};
				break;
			case Magenta:
				colorvals = new int[]{255,0,255};
				break;
			case White:
				colorvals = new int[]{255,255,255};
				break;
			case Purple:
				colorvals = new int[]{140, 0, 255};
				break;
			default:
				//nada
				break;
		}
		return(colorvals);
	}

    
}
