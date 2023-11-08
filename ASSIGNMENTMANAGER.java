import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Student {
    private String name;
    private int prnNo;

    public Student(String name, int prnNo) {
        this.name = name;
        this.prnNo = prnNo;
    }

    public String getName() {
        return name;
    }

    public int getPrnNo() {
        return prnNo;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", PRN No: " + prnNo;
    }
}

class Trainer {
    private String name;

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}

class Assignment {
    Trainer train;
    private String title;
    private Date date;
    private String description;
    private Student assignee;
    private Trainer trainer;

    public Assignment(String title, Date date, String description, Student assignee, Trainer trainer) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.assignee = assignee;
        this.trainer = trainer;
    }

    Assignment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Student getAssignee() {
        return assignee;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Title: " + title + "\nDate: " + sdf.format(date) + "\nDescription: " + description + "\nAssignee: " + assignee.getName() + "\nTrainer: " + trainer.getName();
    }
}


    

public class ASSIGNMENTMANAGER{
        
        private ArrayList<Assignment> assignments = new ArrayList<>();

    public void createAssignment(String title, Date date, String description, Student assignee, Trainer trainer) {
        Assignment assignment = new Assignment(title, date, description, assignee, trainer);
        assignments.add(assignment);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void saveAssignmentsToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(assignments);
            System.out.println("Assignments saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAssignmentsFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            assignments = (ArrayList<Assignment>) ois.readObject();
            System.out.println("Assignments loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkAssignmentsByDate(Date targetDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Assignment assignment : assignments) {
            if (assignment.getDate().equals(targetDate)) {
                System.out.println(assignment);
                System.out.println();
            }
        }
    }

    public void showAssignments() {
        for (Assignment assignment : assignments) {
            System.out.println(assignment);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ASSIGNMENTMANAGER manager = new ASSIGNMENTMANAGER();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Assignment Manager Menu:");
            System.out.println("1. Create Assignment");
            System.out.println("2. Add Assignment to Collection");
            System.out.println("3. Save Assignments to File");
            System.out.println("4. Load Assignments from File");
            System.out.println("5. Check Assignments by Date");
            System.out.println("6. Show All Assignments");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    String PRN_No = sc.nextLine();
                    System.out.print("Enter Assignment Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Assignment Date (dd-MM-yyyy): ");
                    String dateStr = sc.nextLine();
                    Date date;
                    try {
                        date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use dd-MM-yyyy.");
                        continue;
                    }

                    System.out.print("Enter Assignment Description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter Student's Name: ");
                    String studentName = sc.nextLine();

                    System.out.print("Enter Student's PRN: ");
                    int prnNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Trainer's Name: ");
                    String trainerName = sc.nextLine();

                    Student assignee = new Student(studentName, prnNo);
                    Trainer trainer = new Trainer(trainerName);

                    manager.createAssignment(title, date, description, assignee, trainer);
                    break;

                case 2:
                    Assignment assignment = new Assignment();
                    manager.addAssignment(assignment);
                    System.out.println("Assignment added to the collection.");
                    break;

                case 3:
                    System.out.print("Enter the filename to save assignments: ");
                    String saveFileName = sc.nextLine();
                    manager.saveAssignmentsToFile(saveFileName);
                    break;

                case 4:
                    System.out.print("Enter the filename to load assignments: ");
                    String loadFileName = sc.nextLine();
                    manager.loadAssignmentsFromFile(loadFileName);
                    break;

                case 5:
                    System.out.print("Enter the date to check assignments (dd-MM-yyyy): ");
                    String targetDateStr = sc.nextLine();
                    try {
                        Date targetDate = new SimpleDateFormat("dd-MM-yyyy").parse(targetDateStr);
                        manager.checkAssignmentsByDate(targetDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use dd-MM-yyyy.");
                    }
                    break;

                case 6:
                    manager.showAssignments();
                    break;

                case 7:
                    System.out.println("Exiting the Assignment Manager.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}    