package ELC;

import java.util.Scanner;

import BLC.TrainBLC;
import Beans.ManagementBean;
import Beans.TrainDetailsBean;
import Beans.TrainUserBean;

 public class TrainELC {
	public static void main(String[] args) {
		Scanner sc=new Scanner( System.in);
		System.out.println("\t********** WELCOME TO IRCTC **********");
		while(true) {
		System.out.println("\t1.User");
		System.out.println("\t2.Management");
		System.out.println("\t3.Exit");
		System.out.println("Enter Your Choice : ");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			
			boolean b=true;
			while(b) {
				System.out.println("\t***** IRCTC USER LOGIN *****");
				System.out.println("\t1.Existing User");
				System.out.println("\t2.New User");
				System.out.println("\t3.Back to Main Menu");
				System.out.println("Enter your choice : ");
				int userChoice=sc.nextInt();
				switch(userChoice) {
				
				case 1: System.out.println("\t****Existing user****");
				TrainUserBean tub1=new TrainUserBean();
				 
				System.out.print("Enter UserId :");
				tub1.setUserId(sc.nextInt());
				
				 
				System.out.print("Enter user Password : ");
				tub1.setUserPassword(sc.next());
				 
				
				boolean status=new TrainBLC().loginUser(tub1);
				if(status) {
					System.out.println("Login Successful! Welcome ");	
					boolean userMenu = true;

			        while (userMenu) {
			            System.out.println("\n===== USER MENU =====");
			            System.out.println("\t1. View All Trains");
			            System.out.println("\t2. Book Train Ticket");
			            System.out.println("\t3. View My Bookings");
			            System.out.println("\t4. Cancel Ticket");
			            System.out.println("\t5. Back to Main Menu");
			            System.out.print("Enter your choice: ");
			            int option =sc.nextInt();
			            
			            switch (option) {
		                case 1:
		                    new TrainBLC().showAllTrains();
		                    break;

		                case 2:

 
    System.out.println("===== BOOK TRAIN TICKET =====");

    new TrainBLC().showAllTrains();

    TrainDetailsBean tdb = new TrainDetailsBean();
    Beans.BookingBean bb = new Beans.BookingBean();

    System.out.print("Enter your User ID: ");
    int bookingUserId = sc.nextInt();
    bb.setUserId(bookingUserId);

    System.out.print("Enter Train Number: ");
    int selectedTrain = sc.nextInt();
    bb.setTrainNumber(selectedTrain);

    System.out.print("Enter number of seats: ");
    int seats = sc.nextInt();

    boolean booked = new TrainBLC().bookTicket(bb, seats);

    if (booked) {
        System.out.println("\n===== BOOKING SUCCESSFUL =====");
        
    } else {
        System.out.println("Booking failed. Please check the train and seat availability.");
    }
    break;


		                case 3:

 
		                	System.out.print("Enter your User ID: ");
		                	int searchUserId = sc.nextInt();

		                	new TrainBLC().showMyBookings(searchUserId);
		                	break;

		                case 4:

 
		                	System.out.print("Enter PNR Number: ");
		                	long pnr = sc.nextLong();

		                	new TrainBLC().cancelTicket(pnr);
		                	break;
 

		                case 5:
		                    userMenu = false;
		                    break;

		                default:
		                    System.out.println("Invalid Choice!");
		            }
		        }
		    } 
				else {  
		    	 System.out.println("Invalid UserId or Password...");
				}
				break;
				
				case 2:
					 
					System.out.println("\t ***New User Registration**** ");
				TrainUserBean tub=new TrainUserBean();
				System.out.println("Enter User Id");
				tub.setUserId(sc.nextInt());
				sc.nextLine();
				System.out.println("Enter User Name");
				tub.setUserName(sc.nextLine());
				System.out.println("Enter user phone number");
				tub.setUserPhoneNumber(sc.nextLong());
				sc.nextLine();
				System.out.println("Enter User Password");
				tub.setUserPassword(sc.nextLine());
				int k=new TrainBLC().registerUser(tub);
				if(k>0) {
					System.out.println("Registration Successful!");
				}
				else {
					System.out.println("Registration Failed!");
				}
				break;
				
				
				case 3:b=false;break;
				default: System.out.println("Invalid Choice!");
				
				}
			}
		
		break;
		case 2:
			System.out.println("\t*****Welcome To Management Login*****"); 
			System.out.println("Enter Your UserId");
			int id=sc.nextInt();
			System.out.println("Enter your Paassword");
			String paassword=sc.nextLine();
			paassword=sc.nextLine();
			ManagementBean mb1=new ManagementBean();
			mb1.setId(id);
			mb1.setPassword(paassword);
			//check wheather given Id and password correct or not
			boolean management= new TrainBLC().checkManagent(mb1);
			if(management==true) {
				System.out.println();
				boolean tt1=true;
				while(tt1) {
				System.out.println("\t**** Choose One Option ****");
				System.out.println();
				System.out.println("\t1.Add Train");
				System.out.println("\t2.Remove Train");
				System.out.println("\t3.Show All Trains");
				System.out.println("\t4.Update Train Using Train Number");
				System.out.println("\t5.LogOut");
				System.out.println();
				System.out.println("Enter Your Choice : ");
				int choice1=sc.nextInt();
				switch(choice1) {
				case 1:System.out.println("**** Add The Train ****");
				System.out.println();
				System.out.println("Enter the Train Number");
				int trainNumber=sc.nextInt();
				System.out.println("Enter the train Name");
				String trainName=sc.nextLine();
				trainName=sc.nextLine();
				System.out.println("Enter Train Source");
				String trainS=sc.nextLine();
				System.out.println("Enter Train Destination");
				String trainD=sc.nextLine();
				System.out.println("Enter Train Time");
				String trainTime=sc.nextLine();
				System.out.println("Enter Train Ticket Price");
				Double trainPrice=sc.nextDouble();
				System.out.println("Enter Available Seats");
				int trainSeat=sc.nextInt();
				TrainDetailsBean tdb=new TrainDetailsBean();
				tdb.setTrainD(trainD);
				tdb.setTrainName(trainName);
				tdb.setTrainNumber(trainNumber);
				tdb.setTrainSeats(trainSeat);
				tdb.setTrainPrice(trainPrice);
				tdb.setTrainS(trainS);
				tdb.setTrainSeats(trainSeat);
				tdb.setTrainTime(trainTime);
				int k1=new TrainBLC().addTrainDetails(tdb);
				if(k1>0) {
					System.out.println("Train Added Successfully \n");
				}
				else {
					System.out.println("Train not Added Successfully... \n");
				}
				break;
				case 2:System.out.println(" **** Remove The Train  **** ");
				System.out.println("Enter Train Number : ");
				int trainNo=sc.nextInt();
				int removed=new TrainBLC().removeTrain(trainNo);
				if(removed>0) {
					System.out.println("Train Removed Successfully");
				}
				else {
					System.out.println("Train not found...");
				}
				break;
				
				case 3:System.out.println(" ***** Show All The Trains ***** ");
				new TrainBLC().showAllTrains();
				break;
				
				case 4:System.out.println(" **** Update Train **** ");
				System.out.println("Enter the Train Number");
				int trainNumber1=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the train Name");
				String trainName1=sc.nextLine();
				trainName=sc.nextLine();
				System.out.println("Enter Train Source");
				String trainS1=sc.nextLine();
				System.out.println("Enter Train Destination");
				String trainD1=sc.nextLine();
				System.out.println("Enter Train Time");
				String trainTime1=sc.nextLine();
				System.out.println("Enter Train Ticket Price");
				Double trainPrice1=sc.nextDouble();
				System.out.println("Enter Available Seats");
				int trainSeat1=sc.nextInt();
				TrainDetailsBean tdb1=new TrainDetailsBean();
				
				tdb1.setTrainNumber(trainNumber1);
				tdb1.setTrainName(trainName1);
				tdb1.setTrainS(trainS1);
				tdb1.setTrainD(trainD1);
				tdb1.setTrainTime(trainTime1);
				tdb1.setTrainPrice(trainPrice1);
				tdb1.setTrainSeats(trainSeat1);
				int k=new TrainBLC().updateTrain(tdb1);
				if(k>0) {
					System.out.println("Train Update Successfully!");
				}
				else {
					System.out.println("Train not found or not updated!");
				}
				break;
				
				case 5:tt1=false;break;
					default:System.out.println("Invalid Choice");
						
				}
				}
				 
			}
			else {
				System.out.println("Invalid Details...");
			}
			break;
		case 3:System.out.println("Visit Again...");break;
		default:System.out.println("Invalid Choice");
		}
	}

	}
}

