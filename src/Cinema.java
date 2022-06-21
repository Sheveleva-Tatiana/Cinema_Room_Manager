package cinema;
import java.util.Scanner;


public class Cinema {

    static public class Info {
        public char[][] newArray;
        public int seatsAll;
        public int rowsAll;
        public int tickets;
        public int currentIncome;
        public int totalIncome;
        public Info() {
            seatsAll = 0;
            rowsAll = 0;
            tickets = 0;
            currentIncome = 0;
            totalIncome = 0;
        }
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        return scanner.nextInt();
    }

    public static void showTheSeats(Info info) {
        System.out.print("\nCinema:\n  ");

        for (int i = 1; i <= info.seatsAll; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < info.rowsAll; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < info.newArray[i].length; j++) {
                System.out.print(' ');
                System.out.print(info.newArray[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void buyAticket(Info info) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        if (row > info.rowsAll || seat > info.seatsAll || row < 1 || seat < 1) {
            System.out.println("Wrong input!");
            buyAticket(info);
            return;
        }
        int price;
        if (info.rowsAll * info.seatsAll < 60) {
            price = 10;
        } else {
            int firstPart = info.rowsAll / 2;
            if (row <= firstPart) {
                price = 10;
            } else {
                price = 8;
            }
        }
        if (info.newArray[row - 1][seat - 1] != 'B') {
            info.newArray[row - 1][seat - 1] = 'B';
            System.out.println();
            System.out.println("Ticket price: ");
            System.out.println("$" + price);
            info.tickets++;
            info.currentIncome += price;
        } else {
            System.out.println("That ticket has already been purchased!");
            buyAticket(info);
            return;
        }
    }

    public static void statistics(Info info) {
        System.out.println("Number of purchased tickets: " + info.tickets);
        double percentage = ((double)info.tickets / ((double)info.seatsAll * (double)info.rowsAll)) * 100;
        System.out.printf("Percentage: %.2f%%%n",  percentage);
        System.out.printf("Current income: $%d%n",  info.currentIncome);
        System.out.printf("Total income: $%d%n",  info.totalIncome);
        System.out.println();
    }

    public static void countTotal(Info info) {
        if (info.rowsAll * info.seatsAll < 60) {
            info.totalIncome = info.rowsAll * info.seatsAll * 10;
        } else {
            int firstPart = info.rowsAll / 2;
            int secondPart = info.rowsAll - firstPart;
            int bigPrice1 = firstPart * info.seatsAll * 10;
            int bigPrice2 = secondPart * info.seatsAll * 8;
            info.totalIncome = bigPrice1 + bigPrice2;
        }
    }

    public static void main(String[] args) {
        Info info = new Info();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        info.rowsAll = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        info.seatsAll = scanner.nextInt();

        info.newArray = new char[info.rowsAll][info.seatsAll];

        for (int i = 0; i < info.rowsAll; i++) {
            for (int j = 0; j < info.seatsAll; j++) {
                info.newArray[i][j] = 'S';
            }
        }

        countTotal(info);

        for (;;) {

            int answer = menu();

            if (answer == 1) {
                showTheSeats(info);
            } else if (answer == 2) {
                buyAticket(info);
            } else if (answer == 3) {
                statistics(info);
            } else if (answer == 0) {
                return;
            }
        }
    }
}