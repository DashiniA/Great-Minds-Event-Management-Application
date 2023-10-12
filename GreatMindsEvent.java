import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Participant {
    int id;
    String name;
    String city;
    String contactNo;
    String email;
    int round1Score;
    int round2Score;

    public Participant(int id, String name, String city, String contactNo, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.contactNo = contactNo;
        this.email = email;
        this.round1Score = 0;
        this.round2Score = 0;
    }

    public int getTotalScore() {
        return round1Score + round2Score;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + city + "\t" + contactNo + "\t" + email + "\t" + round1Score + "\t" + round2Score + "\t" + getTotalScore();
    }
}

public class GreatMindsEvent {
    private static Queue<Participant> participants = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nGreat Minds Event Management System");
            System.out.println("1. Register Participants");
            System.out.println("2. Display Participants");
            System.out.println("3. Search for a particular participant");
            System.out.println("4. Delete a participant");
            System.out.println("5. Insert 2 Rounds Results");
            System.out.println("6. Find out the winners (1st, 2nd, 3rd)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registerParticipant();
                    break;
                case 2:
                    displayParticipants();
                    break;
                case 3:
                    searchParticipant();
                    break;
                case 4:
                    deleteParticipant();
                    break;
                case 5:
                    insertResults();
                    break;
                case 6:
                    findWinners();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerParticipant() {
        System.out.print("Enter participant name: ");
        String name = scanner.next();
        System.out.print("Enter participant city: ");
        String city = scanner.next();
        System.out.print("Enter participant Contact number: ");
        String ContactNo = scanner.next();
        System.out.print("Enter participant email: ");
        String email = scanner.next();

        Participant participant = new Participant(participants.size() + 1, name, city, ContactNo, email);
        participants.add(participant);

        System.out.println("Participant registered successfully with ID " + participant.id);
    }

    private static void displayParticipants() {
        System.out.println("ID\tName\tCity\tContact No\tEmail\tRound 1 Score\tRound 2 Score\tTotal Score");
        for (Participant participant : participants) {
            System.out.println(participant.toString());
        }
    }

    private static void searchParticipant() {
        System.out.print("Enter participant ID to search: ");
        int id = scanner.nextInt();
        for (Participant participant : participants) {
            if (participant.id == id) {
                System.out.println(participant.toString());
                return;
            }
        }
        }
    private static void deleteParticipant() {
    System.out.print("Enter participant ID to delete: ");
    int id = scanner.nextInt();
    Participant participantToRemove = null;
    for (Participant participant : participants) {
        if (participant.id == id) {
            participantToRemove = participant;
            break;
        }
    }
    if (participantToRemove != null) {
        participants.remove(participantToRemove);
        System.out.println("Participant with ID " + id + " deleted successfully.");
    } else {
        System.out.println("Participant with ID " + id + " not found.");
    }
}

private static void insertResults() {
    System.out.print("Enter participant ID: ");
    int id = scanner.nextInt();
    Participant participantToUpdate = null;
    for (Participant participant : participants) {
        if (participant.id == id) {
            participantToUpdate = participant;
            break;
        }
    }
    if (participantToUpdate != null) {
        System.out.print("Enter Round 1 Score: ");
        int round1Score = scanner.nextInt();
        participantToUpdate.round1Score = round1Score;

        System.out.print("Enter Round 2 Score: ");
        int round2Score = scanner.nextInt();
        participantToUpdate.round2Score = round2Score;

        System.out.println("Results inserted successfully.");
    } else {
        System.out.println("Participant with ID " + id + " not found.");
    }
}

private static void findWinners() {
    if (participants.isEmpty()) {
        System.out.println("No participants registered yet.");
        return;
    }
    Queue<Participant> winners = new LinkedList<>();
    int maxScore = Integer.MIN_VALUE;
    for (Participant participant : participants) {
        int totalScore = participant.getTotalScore();
        if (totalScore > maxScore) {
            winners.clear();
            winners.add(participant);
            maxScore = totalScore;
        } else if (totalScore == maxScore) {
            winners.add(participant);
        }
    }
    if (winners.isEmpty()) {
        System.out.println("No winners found.");
    } else {
        System.out.println("Winners:");
        int position = 1;
        while (!winners.isEmpty() && position <= 3) {
            Participant winner = winners.poll();
            System.out.println(position + ". " + winner.name + " (" + winner.city + ") - Total Score: " + winner.getTotalScore());
            position++;
        }
    }
}
}

