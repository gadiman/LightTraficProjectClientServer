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
	private Event64 evNumOfCar,evNextCrossRoad,evNextLightTraffic,evSendCar;
	private Integer countOfCrossRoads;
	private String numOfCrossRoad;
	private static Integer carNum;
	Random rand;


	public CarsMaker(JPanel myPanel, ShloshaAvot myRamzor, int key ,
					 String nameOfCrossRoad,Event64 evNumOfCar_ ,Event64 evNextLightTraffic
			,Event64 evNextCrossRoad,Event64 evSendCar) {
		this.myPanel = myPanel;
		this.myRamzor = myRamzor;
		this.key = key;
		this.evNumOfCar = evNumOfCar_;
		this.evNextCrossRoad = evNextCrossRoad;
		this.evNextLightTraffic = evNextLightTraffic;
		this.evSendCar = evSendCar;
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
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										carNum = carCreatNumber.getInstance() + -1;
										evSendCar.sendEvent(carNum);
										evNextLightTraffic.sendEvent(1);
										evNextCrossRoad.sendEvent(2);
										break;
									case 3:
									case 4:
										if (evNumOfCar.arrivedEvent()) {
											int tmp = rand.nextInt(2) + 3; //Random value [3,4] range
											carNum = (Integer) evNumOfCar.waitEvent();
											new CarMoving(myPanel, myRamzor, tmp, carNum);
										}
										break;
								}
							case "2"://CrossRoad 2
								switch (key) {
									case 1:
										if (evNumOfCar.arrivedEvent()) {
											carNum = (Integer) evNumOfCar.waitEvent();
											new CarMoving(myPanel, myRamzor, key,carNum );
											evSendCar.sendEvent(carNum);
											evNextLightTraffic.sendEvent(1);
											evNextCrossRoad.sendEvent(3);
										}
										break;
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										carNum = carCreatNumber.getInstance() + -1;
										evSendCar.sendEvent(carNum);
										evNextLightTraffic.sendEvent(1);
										evNextCrossRoad.sendEvent(3);
										break;
									case 3:
									case 4:
										if (evNumOfCar.arrivedEvent()) {
											int tmp_1 = rand.nextInt(2) + 3; //Random value [3,4] range
											carNum =(Integer) evNumOfCar.waitEvent();
											new CarMoving(myPanel, myRamzor, tmp_1,carNum);

											if (tmp_1 == 3){
												evSendCar.sendEvent(carNum);
												evNextLightTraffic.sendEvent(3);
												evNextCrossRoad.sendEvent(1);
											}
										}
										break;
								}
							case "3"://CrossRoad 3
								switch (key) {
									case 1:
										if (evNumOfCar.arrivedEvent()) {
											carNum = (Integer) evNumOfCar.waitEvent();
											new CarMoving(myPanel, myRamzor, key,carNum );
										}
										break;
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance());
										break;
									case 3:
									case 4:
										int tmp_2 = rand.nextInt(2) + 3; //Random value [3,4] range
										new CarMoving(myPanel, myRamzor, tmp_2, carCreatNumber.getInstance());
										if (tmp_2 == 3){
											evSendCar.sendEvent(carNum);
											evNextLightTraffic.sendEvent(3);
											evNextCrossRoad.sendEvent(2);
										}

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
