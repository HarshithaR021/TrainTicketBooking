package BLC;
 
 
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Beans.BookingBean;
import Beans.ManagementBean;
import Beans.TrainDetailsBean;
import Beans.TrainUserBean;
import DataBaseDetails.CreateConnection;

public class TrainBLC {
	private String destination;
	public boolean checkManagent(ManagementBean mb) {
		boolean b=false;
		
		try {
			 Connection con=CreateConnection.getCon();
		
		PreparedStatement ps=con.prepareStatement
				("select * from management where id=? and password=? ");
		ps.setInt(1, mb.getId());
		ps.setString(2,mb.getPassword());
		ResultSet rs=ps.executeQuery();
		b=rs.next();
			
		} catch (Exception e) {
		 e.printStackTrace();
		}
		return b;
	}
public int addTrainDetails(TrainDetailsBean tdb) {
	int k=0;
	try {
		Connection con=CreateConnection.getCon();
		PreparedStatement ps=con.prepareStatement
				( " insert into traindetails values(?,?,?,?,?,?,?)");
		ps.setInt(1, tdb.getTrainNumber());
		ps.setString(2,tdb.getTrainName());
		ps.setString(3,tdb.getTrainS());
		ps.setString(4,tdb.getTrainD());
		ps.setString(5,tdb.getTrainTime());
		ps.setDouble(6, tdb.getTrainPrice());
		ps.setInt(7, tdb.getTrainSeats());
		k=ps.executeUpdate();
		
	} catch (Exception e) {
		 e.printStackTrace();
	}
	return k;
}
public int removeTrain(int trainNumber) {
	int k=0;
	try {
		Connection con=CreateConnection.getCon();
		PreparedStatement ps=con.prepareStatement
				(" delete from traindetails where trainNumber=?");
		ps.setInt(1, trainNumber);
		k=ps.executeUpdate();
				}
	catch (Exception e) {
		e.printStackTrace();
	}
	return k;
}

public void showAllTrains() {
	try {
		Connection con=CreateConnection.getCon();
		PreparedStatement ps=con.prepareStatement
				(" select * from traindetails");
		ResultSet rs=ps.executeQuery();
		boolean b=false;
		System.out.println("\t ***** ALL TRAINS *****");
		while(rs.next()) {
			b=true;
			System.out.println("Train Number : "+rs.getInt("trainNumber"));
			System.out.println("Train Name :"+rs.getString("trainName"));
			System.out.println("Source : "+rs.getString("trainS"));
			System.out.println("Destination :"+rs.getString("trainD"));
			System.out.println("Train Time :"+rs.getString("trainTime"));
			System.out.println("Ticket Price :"+rs.getDouble("trainPrice"));
			System.out.println("Available Seats :"+rs.getInt("trainSeat"));
			System.out.println("-------------------------------------");
		}
		if(!b) {
			System.out.println("No Train Available");
		}
	} catch (Exception e) {
		 e.printStackTrace();
	}
	}
	public int updateTrain(TrainDetailsBean tdb) {
		int k=0;
		try {
			Connection con=CreateConnection.getCon();
			PreparedStatement ps= con.prepareStatement
					("UPDATE traindetails SET trainName=?, trainS=?, trainD=?, " +" trainTime=?,trainPrice=?,trainSeat=? where trainNumber=?");
			ps.setString(1,tdb.getTrainName());
			ps.setString(2,tdb.getTrainS());
			ps.setString(3,tdb.getTrainD());
			ps.setString(4,tdb.getTrainTime());
			ps.setDouble(5,tdb.getTrainPrice());
			ps.setInt(6,tdb.getTrainSeats());
			ps.setInt(7,tdb.getTrainNumber());
			k=ps.executeUpdate();

		} catch (Exception e) {
			 e.printStackTrace();
		}
		return k;
	}
public int registerUser(TrainUserBean tub) {
	int k=0;
	try {
		Connection con=CreateConnection.getCon();
		PreparedStatement ps=con.prepareStatement
				(" Insert into trainUser(userId,userName,userPhoneNumber,userPassword) values(?,?,?,?)");
		ps.setInt(1,tub.getUserId());
		ps.setString(2,tub.getUserName());
		ps.setLong(3,tub.getUserPhoneNumber());
		ps.setString(4,tub.getUserPassword());
		k=ps.executeUpdate();
	} catch (Exception e) {
		 e.printStackTrace();
	}
	return k;
}
public boolean loginUser(TrainUserBean tub) {
	 boolean status=false;
	try {
		Connection con=CreateConnection.getCon();
		PreparedStatement ps=con.prepareStatement
				(" SELECT * FROM trainuser WHERE userId=? AND userPassword=?");
		ps.setInt(1,tub.getUserId());
		ps.setString(2,tub.getUserPassword());
		ResultSet rs=ps.executeQuery();
		status=rs.next();
	}
	catch (Exception e) {
		 e.printStackTrace();
	}
	return status;
}


public boolean bookTicket(BookingBean bb, int seats) {
    try {
        Connection con = CreateConnection.getCon();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM traindetails WHERE trainNumber=?");

        ps.setInt(1, bb.getTrainNumber());
        ResultSet rs = ps.executeQuery();

        if (!rs.next() || seats <= 0 ||
                rs.getInt("trainSeat") < seats) {
            System.out.println("Invalid train or seats unavailable!");
            return false;
        }

        bb.setSource(rs.getString("trainS"));
        bb.setDestination(rs.getString("trainD"));
        bb.setTime(rs.getString("trainTime"));
        bb.setPrice(rs.getDouble("trainPrice") * seats);
        bb.setPnr(System.currentTimeMillis());

        PreparedStatement ps1 = con.prepareStatement(
            "UPDATE traindetails SET trainSeat=trainSeat-? " +
            "WHERE trainNumber=? AND trainSeat>=?");

        ps1.setInt(1, seats);
        ps1.setInt(2, bb.getTrainNumber());
        ps1.setInt(3, seats);
        ps1.executeUpdate();

        PreparedStatement ps2 = con.prepareStatement(
            "INSERT INTO booking " +
            "(userId,pnr,price,trainNumber,Source,destination,time,ticketCount) " +
            "VALUES(?,?,?,?,?,?,?,?)");

        ps2.setInt(1, bb.getUserId());
        ps2.setLong(2, bb.getPnr());
        ps2.setDouble(3, bb.getPrice());
        ps2.setInt(4, bb.getTrainNumber());
        ps2.setString(5, bb.getSource());
        ps2.setString(6, bb.getDestination());
        ps2.setString(7, bb.getTime());
        ps2.setInt(8, seats);

        ps2.executeUpdate();

        System.out.println("Ticket Booked Successfully!");
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public void showMyBookings(int userId) {
    try {
        Connection con = CreateConnection.getCon();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM booking WHERE userId = ?"
        );

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        boolean found = false;

        System.out.println("\n===== MY BOOKINGS =====");

        while (rs.next()) {
            found = true;

            System.out.println("PNR Number: " + rs.getLong("pnr"));
            System.out.println("Train Number: " + rs.getInt("trainNumber"));
            System.out.println("From: " + rs.getString("Source"));
            System.out.println("To: " + rs.getString("destination"));
            System.out.println("Departure: " + rs.getString("time"));
            System.out.println("Total Price: Rs. " + rs.getDouble("price"));

            System.out.println("-----------------------------");
        }

        if (!found) {
            System.out.println("No bookings found for this user.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean cancelTicket(long pnr) {
    try {
        Connection con = CreateConnection.getCon();

        PreparedStatement ps = con.prepareStatement(
            "SELECT trainNumber, ticketCount FROM booking WHERE pnr=?");

        ps.setLong(1, pnr);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            System.out.println("Invalid PNR number!");
            return false;
        }

        int trainNumber = rs.getInt("trainNumber");
        int seats = rs.getInt("ticketCount");

        PreparedStatement ps1 = con.prepareStatement(
            "UPDATE traindetails SET trainSeat=trainSeat+? WHERE trainNumber=?");

        ps1.setInt(1, seats);
        ps1.setInt(2, trainNumber);
        ps1.executeUpdate();

        PreparedStatement ps2 = con.prepareStatement(
            "DELETE FROM booking WHERE pnr=?");

        ps2.setLong(1, pnr);
        ps2.executeUpdate();

        System.out.println("Ticket cancelled successfully!");
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
