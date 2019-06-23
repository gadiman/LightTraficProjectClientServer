package LightTraffic;

import javax.swing.JPanel;
import java.util.Random;

/**
 * It represent a cars maker.
 * @author Arie and Gad.
 */
public class CarsMaker extends Thread
{
	private JPanel myPanel;
	private ShloshaAvot myRamzor;
	private int key;
	private Event64 evNumOfCar,evNextCrossRoad,evNextLightTraffic;
	private Integer countOfCrossRoads;
	private String numOfCrossRoad;
	private static Integer carNum;
	Random rand;


	public CarsMaker(JPanel myPanel, ShloshaAvot myRamzor, int key ,
					 String nameOfCrossRoad,Event64 evNumOfCar_ ,Event64 evNextLightTraffic
			,Event64 evNextCrossRoad) {
		this.myPanel = myPanel;
		this.myRamzor = myRamzor;
		this.key = key;
		this.evNumOfCar = evNumOfCar_;
		this.evNextCrossRoad = evNextCrossRoad;
		this.evNextLightTraffic = evNextLightTraffic;
		numOfCrossRoad = nameOfCrossRoad;
		this.countOfCrossRoads =3;
		rand = new Random();
        carNum = carCreatNumber.getInstance();
		setDaemon(true);
		start();
	}

	public void run() {
		try {
			while (true) {
				sleep(300);
				if (!myRamzor.isStop()) {
						switch (numOfCrossRoad) {
							case "1": //CrossRoad 1
								switch (key) {
									case 1:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										//ToDo SENT EVENT
										break;
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										//ToDo SENT EVENT
										break;
									case 3:
									case 4:
										if (evNumOfCar.arrivedEvent()) {
											int tmp = rand.nextInt(2) + 3; //Random value [3,4] range
											new CarMoving(myPanel, myRamzor, tmp, (Integer) evNumOfCar.waitEvent());
											if (tmp == 3)
												System.out.println("");
											//Todo send Event
												else
												System.out.println("");
											//Todo send Event
										}
										break;
								}

								break;

							case "2"://CrossRoad 2
								switch (key) {
									case 1:
										if (evNumOfCar.arrivedEvent())
											new CarMoving(myPanel, myRamzor, key, (Integer) evNumOfCar.waitEvent());
										//ToDo SENT EVENT
										break;
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										//ToDo SENT EVENT
										break;
									case 3:
									case 4:
										if (evNumOfCar.arrivedEvent()) {
											int tmp_1 = rand.nextInt(2) + 3; //Random value [3,4] range
											new CarMoving(myPanel, myRamzor, tmp_1, (Integer) evNumOfCar.waitEvent());
											if (tmp_1 == 3)
											//Todo send Event
												System.out.println("");

											else
											//Todo send Event
												System.out.println("");

										}
										break;
								}

								break;

							case "3"://CrossRoad 3
								switch (key) {
									case 1:
										if (evNumOfCar.arrivedEvent())
											new CarMoving(myPanel, myRamzor, key, (Integer) evNumOfCar.waitEvent());
										//ToDo SENT EVENT
										break;
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										//ToDo SENT EVENT
										break;
									case 3:
									case 4:
										int tmp_2 = rand.nextInt(2) + 3; //Random value [3,4] range
										new CarMoving(myPanel, myRamzor, tmp_2, carCreatNumber.getInstance());
										if (tmp_2 == 3)
											System.out.println("");

											//Todo send Event
												else
											System.out.println("");

										//Todo send Event

										break;
								}


						}

					}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
