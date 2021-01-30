package core;

import java.time.LocalDate;

public class CalculateDays {

	static int todayYear, todayMonth, todayDay;

	CalculateDays() {

	}
	
	
	public static int countDaysOfMonths(int tempYear,
			int tempMonth, int tempDay, int numOfDays) {

		while ( tempMonth != todayMonth ) {
			switch ( tempMonth ) {
			case 4:
			case 6:
			case 9:
			case 11: {numOfDays += 30; break;}
			case 2: {numOfDays += 28; if ( tempYear % 4 == 0 ) numOfDays++; break;}
			default: {numOfDays += 31; break;} // months: 1,3,5,7,8,10,12
			}
			tempMonth++;
		}
		return numOfDays;
	}

	
	public static boolean isBornInTheFuture(int y, int m, int d) {
		if ( todayYear < y ) {
			return true;
		}
		if ( todayYear == y && todayMonth < m) {
			return true;
		}
		if ( todayYear == y && todayMonth == m && todayDay < d) {
			return true;
		}
		return false;
	}
	
	
	public static int convertMonthToInteger(String birthdayMonth) {
		
		switch (birthdayMonth) {
		case "January": return 1;
		case "February": return 2;
		case "March": return 3;
		case "April": return 4;
		case "May": return 5;
		case "June": return 6;
		case "July": return 7;
		case "August": return 8;
		case "September": return 9;
		case "October": return 10;
		case "November": return 11;
		case "December": return 12;
		}
		return -1; // unreachable
		
	}

	
	public static int calculateDays(String birthdayYear, 
			String birthdayMonth, String birthdayDay) {
		LocalDate currentDate = LocalDate.now();
		todayYear = currentDate.getYear();
		todayMonth = currentDate.getMonthValue();
		todayDay = currentDate.getDayOfMonth();
		int numOfDays = 0;
		
		Integer tempYear, tempMonth, tempDay;
		tempYear = Integer.parseInt(birthdayYear);
		tempMonth = convertMonthToInteger(birthdayMonth);
		tempDay = Integer.parseInt(birthdayDay);
		
		if ( isBornInTheFuture(tempYear, tempMonth, tempDay) ) {
			return -1;
		}
		
		while (true) {
			if ( tempYear == todayYear ) {
				if ( tempMonth == todayMonth ) {
					while ( tempDay != todayDay ) {
						tempDay++;
						numOfDays++;
					}
					return numOfDays;
				}
				else {
					// count the days left in the month
					switch ( tempMonth ) {
					case 4:
					case 6:
					case 9:
					case 11: {numOfDays += (30 - tempDay); break;}
					case 2: {numOfDays += (28 - tempDay); if ( tempYear % 4 == 0 ) numOfDays++; break;}
					default: {numOfDays += (31 - tempDay); break;} // months: 1,3,5,7,8,10,12
					}
					tempMonth++;

					// count the days in the months until todayMonth
					numOfDays = countDaysOfMonths(tempYear,
							tempMonth, tempDay, numOfDays);

					// count the days until todayDay
					numOfDays += todayDay;
					return numOfDays;
				}
			}

			else { // tempYear != todayYear

				// count the days left in the month
				switch ( tempMonth ) {
				case 4:
				case 6:
				case 9:
				case 11: {numOfDays += (30 - tempDay); break;}
				case 2: {numOfDays += (28 - tempDay); if ( tempYear % 4 == 0 ) numOfDays++; break;}
				default: {numOfDays += (31 - tempDay); break;} // months: 1,3,5,7,8,10,12
				}
				tempMonth++;

				// count months left in the year
				while ( tempMonth != 12 + 1 ) {

					switch ( tempMonth ) {
					case 4:
					case 6:
					case 9:
					case 11: {numOfDays += 30; break;}
					case 2: {numOfDays += 28; if ( tempYear % 4 == 0 ) numOfDays++; break;}
					default: {numOfDays += 31; break;} // months: 1,3,5,7,8,10,12
					}
					tempMonth++;
					
				}
				tempYear++;

				// count the years until todayYear
				while ( tempYear != todayYear ) {
					// check if leap year
					if ( tempYear % 4 == 0 ) {
						numOfDays += 366;
					}
					else {
						numOfDays += 365;
					}
					tempYear++;
				}

				// reassign tempDay and tempMonth
				tempDay = 1; // day 1 of month
				tempMonth = 1; // january of year todayYear

				// count the days in the months until todayMonth
				numOfDays = countDaysOfMonths(tempYear,
						tempMonth, tempDay, numOfDays);

				// count the days until todayDay
				numOfDays += todayDay;
				return numOfDays;
			}
		}
		
	}


	public static String[] fillYearsJComboBox() {
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		int n = currentYear - 1900 + 2;
		String[] birthdayYear;
		birthdayYear = new String[n];
		birthdayYear[0] = "Year";
		for (int i=0; i<n-1; i++) {
			birthdayYear[i+1] = Integer.toString(currentYear - i);
		}
		return birthdayYear;

	}


}
