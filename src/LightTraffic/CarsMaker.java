package LightTraffic;

import javax.swing.JPanel;
import java.io.PrintWriter;
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

	private String numOfCrossRoad;
	private static Integer carNum;
	Random rand;
	PrintWriter buffer;
	LightTrafficQueue lightTrafficQueue;


	public CarsMaker(JPanel myPanel, ShloshaAvot myRamzor, int key ,
					 String nameOfCrossRoad, PrintWriter buffer,LightTrafficQueue lightTrafficQueue) {
		this.myPanel = myPanel;
		this.myRamzor = myRamzor;
		this.key = key;
		this.buffer = buffer;
		this.lightTrafficQueue = lightTrafficQueue;
		numOfCrossRoad = nameOfCrossRoad;
		rand = new Random();
		setDaemon(true);
		start();
	}

	public void run() {
		try {
			while (true) {
				sleep(300);
				if (!myRamzor.isStop()) {
						switch (numOfCrossRoad) {
							case "_1": //CrossRoad 1
								switch (key) {
									case 1:
									case 2:
										carNum = carCreatNumber.getInstance() + -1;
										new CarMoving(myPanel, myRamzor, key, carNum+1,1,2,buffer,numOfCrossRoad);


										break;
									case 3:
									case 4:
										if (!lightTrafficQueue.LT3.isEmpty()) {
											int tmp = rand.nextInt(2) + 3; //Random value [3,4] range
											carNum = lightTrafficQueue.LT3.poll();
											new CarMoving(myPanel, myRamzor, tmp, carNum,-1,-1,buffer,numOfCrossRoad);
										}
										break;
								}
								break;
							case "_2"://CrossRoad 2
								switch (key) {
									case 1:
										if (!lightTrafficQueue.LT1.isEmpty()) {
											carNum = lightTrafficQueue.LT1.poll();
											new CarMoving(myPanel, myRamzor, key,carNum ,1,3,buffer,numOfCrossRoad);
										}
										break;
									case 2:
										carNum = carCreatNumber.getInstance() + -1;
										new CarMoving(myPanel, myRamzor, key, carNum+1,1,3,buffer,numOfCrossRoad);
										break;
									case 3:
									case 4:
										if (!lightTrafficQueue.LT3.isEmpty()) {
											int tmp_1 = rand.nextInt(2) + 3; //Random value [3,4] range
											carNum =lightTrafficQueue.LT1.poll();

											if (tmp_1 == 3){
												new CarMoving(myPanel, myRamzor, tmp_1,carNum,3,1,buffer,numOfCrossRoad);
											}
											else
												new CarMoving(myPanel, myRamzor, tmp_1,carNum,-1,-1,buffer,numOfCrossRoad);
										}
										break;
								}
								break;
							case "_3"://CrossRoad 3
								switch (key) {
									case 1:
										if (!lightTrafficQueue.LT1.isEmpty()) {
											carNum = lightTrafficQueue.LT1.poll();
											new CarMoving(myPanel, myRamzor, key,carNum,-1,-1,buffer ,numOfCrossRoad);
										}
										break;
									case 2:
										new CarMoving(myPanel, myRamzor, key, carCreatNumber.getInstance(),-1,-1,buffer,numOfCrossRoad);
										break;
									case 3:
									case 4:
										int tmp_2 = rand.nextInt(2) + 3; //Random value [3,4] range
										carNum = carCreatNumber.getInstance() -1;
										new CarMoving(myPanel, myRamzor, tmp_2,carNum+1,3,2 ,buffer,numOfCrossRoad);
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
