package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BujoFileWalker;
import cs3500.pa05.model.CheckedRunnable;
import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.model.SchedulingItem;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.WeekJson;
import cs3500.pa05.view.JournalView;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * A controller class that manages the operations of the Journal. It implements the IController
 * interface which provides a contract for all controllers.
 */
public class JournalController implements Icontroller {
  @FXML
  private ComboBox<Button> comboC;
  @FXML
  private ListView<Button> monView;
  @FXML
  private ListView<Button> tuesView;

  @FXML
  private ListView<Button> wedView;

  @FXML
  private ListView<Button> thursView;

  @FXML
  private ListView<Button> friView;
  @FXML
  private ListView<Button> satView;

  @FXML
  private ListView<Button> sunView;

  @FXML
  private Button task;

  @FXML
  private Button saveFile;

  @FXML
  private Button event;

  @FXML
  private Button maxE;

  @FXML
  private Button maxT;

  @FXML
  private Button title;
  @FXML
  private Label weekTitle;

  @FXML
  private Label maxTaskCount;

  @FXML
  private Label popupLabel;

  @FXML
  private Label maxEventCount;

  @FXML
  private Button royal;

  @FXML
  private Button redYellow;
  @FXML
  private Button pinkBlue;

  @FXML
  private Button close;

  @FXML
  private Button sunday;

  @FXML
  private Button monday;
  @FXML
  private Button tuesday;

  @FXML
  private Button wednesday;

  @FXML
  private Button thursday;

  @FXML
  private Button friday;

  @FXML
  private Button saturday;

  @FXML
  private Button queueButton;

  @FXML
  private ListView<String> queue;

  @FXML
  private Button closeQueue;

  @FXML
  private SplitPane splitPane;

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button edit;

  @FXML
  private HBox listBox;

  @FXML
  private Button delete;

  @FXML
  private Label totalEvents;

  @FXML
  private Label totalTasks;

  @FXML
  private Label tasksComplete;

  @FXML
  private ProgressBar satProg;
  @FXML
  private ProgressBar sunProg;
  @FXML
  private ProgressBar monProg;
  @FXML
  private ProgressBar tuesProg;
  @FXML
  private ProgressBar wedProg;
  @FXML
  private ProgressBar thursProg;

  @FXML
  private ProgressBar friProg;

  @FXML
  private Label satTasks;
  @FXML
  private Label sunTasks;
  @FXML
  private Label monTasks;
  @FXML
  private Label tuesTasks;
  @FXML
  private Label wedTasks;
  @FXML
  private Label thursTasks;
  @FXML
  private Label friTasks;
  private static String bad_input = "BAD_INPUT";
  private final Stage stage;

  private final JournalWeek week;

  private FileReader fileReader;
  private final ObjectMapper mapper = new ObjectMapper();

  private Map<String, ListView<Button>> dayViews = new HashMap<>();

  /**
   * Constructs a JournalController object.
   *
   * @param stage The stage where the JournalController will be displayed.
   * @param week  The JournalWeek that will be managed by the controller.
   */
  public JournalController(Stage stage, JournalWeek week) {
    this.stage = stage;
    this.week = week;
  }

  /**
   * Starts the execution of the journal controller and initializes the buttons and loads saved
   * files.
   *
   * @throws IOException if an I/O error occurs.
   */
  @FXML
  public void run() throws IOException {
    buttons();
  }

  /**
   * Sets button actions for FXML files
   *
   * @throws IOException if a method call throws an IOException
   */
  private void buttons() throws IOException {
    splitPane.setDividerPositions(0);
    anchorPane.getChildren().remove(closeQueue);
    loadSavedFiles();
    queueButton.setOnAction(e -> handleException(this::showQueue));
    task.setOnAction(e -> handleException(() -> createTask()));
    event.setOnAction(e -> handleException(() -> createEvent()));
    maxT.setOnAction(e -> setTasks());
    maxE.setOnAction(e -> setEvents());
    title.setOnAction(e -> setTitle());
    saveFile.setOnAction(e -> handleException(this::saveToBujo));
    String s = week.getTheme().substring(0, week.getTheme().indexOf("y") + 1);
    royal.setOnAction(e -> handleException(() -> changeTheme(s + "RoyalWeek.fxml")));
    redYellow.setOnAction(e -> handleException(() -> changeTheme(s + "RedAndYellow.fxml")));
    pinkBlue.setOnAction(e -> handleException(() -> changeTheme(s + "BlueAndYellow.fxml")));
    sunday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Sunday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
    monday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Monday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
    tuesday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Tuesday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
    wednesday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Wednesday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
    thursday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Thursday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
    friday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Friday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
    saturday.setOnAction(e -> handleException(() -> changeTheme("changedSchedules/Saturday"
        + week.getTheme().substring(week.getTheme().indexOf("y") + 1))));
  }

  private <E extends Exception> void handleException(CheckedRunnable<E> runnable) {
    try {
      runnable.run();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Loads the given file's data into the calendar
   *
   * @param filePath the path of the file
   * @throws IOException the file was not able to be read
   */
  private void loadFile(String filePath) throws IOException {
    fileReader = new FileReader(filePath);
    JsonParser parser = this.mapper.getFactory().createParser(this.fileReader);
    WeekJson message = parser.readValueAs(WeekJson.class);
    clear(message.theme());
    loadButtonsAndHeadings(message);
    loadDays(message.days());
  }

  /**
   * Loads the individual data of each day into the calendar
   *
   * @param days the list of DayJson representing the 7 days
   * @throws IOException the events or tasks were not loaded properly
   */
  private void loadDays(List<DayJson> days) throws IOException {
    dayViews.put(DayType.MONDAY.rep, monView);
    dayViews.put(DayType.TUESDAY.rep, tuesView);
    dayViews.put(DayType.WEDNESDAY.rep, wedView);
    dayViews.put(DayType.THURSDAY.rep, thursView);
    dayViews.put(DayType.FRIDAY.rep, friView);
    dayViews.put(DayType.SATURDAY.rep, satView);
    dayViews.put(DayType.SUNDAY.rep, sunView);
    for (DayJson d : days) {
      loadTasks(dayViews.get(d.day()), d.tasks());
      loadEvents(dayViews.get(d.day()), d.events());
    }
  }

  /**
   * Loads the given list of tasks into the given ListView
   *
   * @param view  the ListView being added to
   * @param tasks the list of tasks to add
   * @throws IOException there was an error adding the tasks
   */
  private void loadTasks(ListView<Button> view, List<TaskJson> tasks) throws IOException {
    for (TaskJson t : tasks) {
      Task task = new Task(t.name(), t.description(), t.day(), t.bool());
      week.addTask(task.getDay(), task);
      view.getItems().addAll(inCalendar(task, task.getName(), task.toString(), true));
    }
  }

  /**
   * Loads the given list of events into the given ListView
   *
   * @param view   the ListView being added to
   * @param events the list of events to add
   * @throws IOException there was an error adding the events
   */
  private void loadEvents(ListView<Button> view, List<EventJson> events) throws IOException {
    for (EventJson e : events) {
      Event event = new Event(e.name(), e.description(), e.day(), e.start(), e.duration());
      week.addEvent(event.getDay(), event);
      view.getItems().addAll(inCalendar(event, event.getName(), event.toString(), false));
    }
  }

  /**
   * Sets the calendar buttons, title, and theme
   *
   * @param message the json from the bujo file in WeekJson format
   */
  private void loadButtonsAndHeadings(WeekJson message) {
    weekTitle.setText(message.title());
    week.setTheme(message.theme());
    week.setTitle(message.title());
    week.setMaxEvents(message.maxEvents());
    week.setMaxTasks(message.maxTasks());
    if (week.getMaxTasks() == Integer.MAX_VALUE) {
      maxTaskCount.setText("Max Daily Tasks: N/A");
    } else {
      maxTaskCount.setText("Max Daily Tasks: " + week.getMaxTasks());
    }
    if (week.getMaxEvents() == Integer.MAX_VALUE) {
      maxEventCount.setText("Max Daily Events: N/A");
    } else {
      maxEventCount.setText("Max Daily Events: " + week.getMaxEvents());
    }
  }

  /**
   * Shows the Task-Queue
   *
   * @throws IOException there was an error trying to show the Task Queue
   */
  private void showQueue() throws IOException {
    String s = saveToBujo();
    loadFile(s);
    ArrayList<String> taskList = week.getTaskList();
    queue.getItems().addAll(taskList);
    anchorPane.getChildren().add(closeQueue);
    splitPane.setDividerPositions(0.1687);
    closeQueue.setOnAction(e -> closeQueue());
  }

  /**
   * Closes the Task-Queue
   */
  private void closeQueue() {
    splitPane.setDividerPositions(0);
    anchorPane.getChildren().remove(closeQueue);
  }


  /**
   * Changes the theme of the calendar
   *
   * @param theme the desired new theme
   * @throws IOException there was an error trying to set the new theme
   */
  private void changeTheme(String theme) throws IOException {
    week.setTheme(theme);
    String file = saveToBujo();
    clear(theme);
    loadFile(file);
  }

  /**
   * Parses the filesystem to find all .bujo files, adds them to the dropdown box
   *
   * @throws IOException there was an error collecting the .bujo files
   */
  private void loadSavedFiles() throws IOException {
    BujoFileWalker visitor = new BujoFileWalker();
    Files.walkFileTree(Paths.get(""), visitor);
    ArrayList<String> files = visitor.getBujoFiles();
    ArrayList<Button> fileButtons = new ArrayList<>();
    for (String s : files) {
      Button b = new Button(s.substring(s.lastIndexOf("/") + 1));
      b.setOnAction(e -> {
        try {
          changeTheme(week.getTheme());
          loadFile(s);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });
      fileButtons.add(b);
    }
    comboC.getItems().addAll(fileButtons);
  }

  /**
   * Clears the calendar, sets with a new theme
   *
   * @param theme the new theme
   * @throws IOException there was error associated with clearing the calendar
   */
  private void clear(String theme) throws IOException {
    Pane root = (Pane) stage.getScene().getRoot();
    root.getChildren().clear();
    this.week.clear();
    JournalView changeTheme = new JournalView(this, theme);
    stage.setScene(changeTheme.load());
    buttons();
  }

  /**
   * Saves the week as a bujo file
   *
   * @return the filename of the .bujo file
   * @throws IOException there was an error trying to save the file
   */
  private String saveToBujo() throws IOException {
    return week.saveToBujo();
  }

  /**
   * Sets the title of the calendar
   */
  private void setTitle() {
    String t = getInfo("Enter a title for this week's calendar:");
    weekTitle.setText(t);
    week.setTitle(t);
  }

  /**
   * Calculates the weekly statistics and sets them to corresponding annotations
   */
  private void setStatistics() {
    Map<Integer, Control[]> statsView = new HashMap<>();
    statsView.put(0, new Control[] {sunProg, sunTasks});
    statsView.put(1, new Control[] {monProg, monTasks});
    statsView.put(2, new Control[] {tuesProg, tuesTasks});
    statsView.put(3, new Control[] {wedProg, wedTasks});
    statsView.put(4, new Control[] {thursProg, thursTasks});
    statsView.put(5, new Control[] {friProg, friTasks});
    statsView.put(6, new Control[] {satProg, satTasks});

    for (int i = 0; i <= DayType.SATURDAY.numRep; i++) {
      int[] stats = week.getDayTaskInfo(i);
      double progressAmount;
      if (stats[0] == 0) {
        progressAmount = 0;
      } else {
        progressAmount = (double) stats[1] / stats[0];
      }
      String uncompleted = "Uncompleted tasks: " + stats[2];
      Control[] controls = statsView.get(i);
      ProgressBar bar = (ProgressBar) controls[0];
      Label label = (Label) controls[1];
      bar.setProgress(progressAmount);
      label.setText(uncompleted);
    }
    int completedTasks = week.getCompletedTasks();
    double percentageComplete = 0;
    if (week.getTotalTasks() != 0) {
      percentageComplete = (double) completedTasks / week.getTotalTasks() * 100;
    }
    totalTasks.setText("Total Tasks: " + week.getTotalTasks());
    totalEvents.setText("Total Events: " + week.getTotalEvents());
    tasksComplete.setText("Tasks Completed: " + String.format("%.2f", percentageComplete) + "%");
  }

  /**
   * Displays a popup error message
   *
   * @param str the desired text on the popup
   * @throws IOException if the popup failed to be created
   */
  private void showErrorMsg(String str) throws IOException {
    Popup apopup = new Popup();
    FXMLLoader loader;
    loader = new FXMLLoader(getClass().getClassLoader().getResource("myPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    apopup.getContent().add(s.getRoot());
    popupLabel.setText(str);
    apopup.show(this.stage);
    close.setOnAction(e -> apopup.hide());
    edit.setVisible(false);
    delete.setVisible(false);
  }

  /**
   * Adds a given SchedulingItem to calendar
   *
   * @param item     The SchedulingItem
   * @param name     the name of the item
   * @param popupMsg the message to be displayed on the popup
   * @param isTask   whether this item is a task
   * @return the button representing this new SchedulingItem
   * @throws IOException if the SchedulingItem was failed to be put into the calendar
   */
  private Button inCalendar(SchedulingItem item, String name, String popupMsg, boolean isTask)
      throws IOException {
    Popup apopup = new Popup();
    FXMLLoader loader;
    loader = new FXMLLoader(getClass().getClassLoader().getResource("myPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    apopup.getContent().add(s.getRoot());
    popupLabel.setText(popupMsg);

    Button button = new Button(name);
    button.setOnAction(e -> makePopup(apopup));
    close.setOnAction(e -> apopup.hide());
    if (isTask) {
      delete.setOnAction(e -> deleteTask((Task) item, button, apopup));
      edit.setOnAction(e -> handleException(() -> editTask((Task) item, button, apopup)));
      Button complete = new Button("Mark as complete");
      complete.setOnAction(e -> handleException(() -> setComplete((Task) item, apopup)));
      apopup.getContent().add(complete);
    } else {
      delete.setOnAction(e -> deleteEvent((Event) item, button, apopup));
      edit.setOnAction(e -> handleException(() -> editEvent((Event) item, button, apopup)));
    }
    setStatistics();
    return button;
  }


  /**
   * Gets the parent ListView of a SchedulingItem
   *
   * @param old the button to be removed
   * @param pop the popup to be hidden
   */
  private void getView(Button old, Popup pop) {
    pop.hide();
    Parent parent = old.getParent();
    while (parent != null) {
      if (parent instanceof ListView) {
        break;
      }
      parent = parent.getParent();
    }
    ListView<Button> view = (ListView<Button>) parent;
    view.getItems().remove(old);
  }

  /**
   * Deletes the given task
   *
   * @param item the task to be deleted
   * @param old  the button to be deleted
   * @param pop  the popup to be hidden
   */
  private void deleteTask(Task item, Button old, Popup pop) {
    getView(old, pop);
    week.removeTask(item);
    setStatistics();
  }

  /**
   * Deletes the given event
   *
   * @param item the event to be deleted
   * @param old  the button to be deleted
   * @param pop  the popup to be hidden
   */
  private void deleteEvent(Event item, Button old, Popup pop) {
    getView(old, pop);
    week.removeEvent(item);
  }

  /**
   * Edits the given task
   *
   * @param item the task to be edited
   * @param old  the button to be changed
   * @param pop  the popup to be hidden
   * @throws IOException if the editing failed to properly occur
   */
  private void editTask(Task item, Button old, Popup pop) throws IOException {
    deleteTask(item, old, pop);
    createTask();
  }

  /**
   * Edits the given event
   *
   * @param item the event to be edited
   * @param old  the button to be changed
   * @param pop  the popup to be hidden
   * @throws IOException if the editing failed to properly occur
   */
  private void editEvent(Event item, Button old, Popup pop) throws IOException {
    deleteEvent(item, old, pop);
    createEvent();
  }

  /**
   * Sets the give task as complete
   *
   * @param task the task to mark completed
   * @param p    the popup to be hidden
   * @throws IOException if there was an error in marking the task complete
   */
  private void setComplete(Task task, Popup p) throws IOException {
    p.hide();
    task.setCompleted();
    String file = saveToBujo();
    loadFile(file);
  }

  /**
   * Shows the given popup
   *
   * @param p the popup
   */
  @FXML
  private void makePopup(Popup p) {
    p.show(this.stage);
  }

  /**
   * Creates a task
   *
   * @throws IOException if the task failed to be created
   */
  private void createTask() throws IOException {
    String name = getInfo("Enter the name");
    String description = getInfo("Enter the description");
    String day = bad_input;
    while (day.equals(bad_input)) {
      day = validateDay(getInfo("Enter the day"));
    }
    if (week.getDaysTasks(day) >= week.getMaxTasks()) {
      showErrorMsg("Max Tasks Reached!");
    } else {
      Task task = new Task(name, description, day, false);
      week.addTask(day, task);
      properDay(day).getItems().addAll(inCalendar(task, name, task.toString(), true));
    }
  }

  /**
   * Creates an event
   *
   * @throws IOException if the event failed to be created
   */
  private void createEvent() throws IOException {
    String name = getInfo("Enter the name");
    String description = getInfo("Enter the description");
    String day = bad_input;
    String time = bad_input;
    String duration = bad_input;
    while (day.equals(bad_input)) {
      day = validateDay(getInfo("Enter the day"));
    }
    while (time.equals(bad_input)) {
      time = validateTime(getInfo("Enter the time in 00:00 format (24 hr clock)"));
    }
    while (duration.equals(bad_input)) {
      duration = validateDuration(getInfo("Enter the duration in minutes (an integer)"));
    }
    if (week.getDaysEvents(day) >= week.getMaxEvents()) {
      showErrorMsg("Max Events Reached!");
    } else {
      Event event = new Event(name, description, day, time, duration);
      week.addEvent(day, event);
      properDay(day).getItems().addAll(inCalendar(event, name, event.toString(), false));
    }
  }

  /**
   * Finds the associated ListView with a given day
   *
   * @param input the day in string format
   * @return the associated ListView
   */
  private ListView<Button> properDay(String input) {
    if (input.equalsIgnoreCase(DayType.MONDAY.rep)) {
      return monView;
    } else if (input.equalsIgnoreCase(DayType.TUESDAY.rep)) {
      return tuesView;
    } else if (input.equalsIgnoreCase(DayType.WEDNESDAY.rep)) {
      return wedView;
    } else if (input.equalsIgnoreCase(DayType.THURSDAY.rep)) {
      return thursView;
    } else if (input.equalsIgnoreCase(DayType.FRIDAY.rep)) {
      return friView;
    } else if (input.equalsIgnoreCase(DayType.SATURDAY.rep)) {
      return satView;
    } else {
      return sunView;
    }
  }

  /**
   * Gets info from a user using a TextDialog
   *
   * @param msg the prompt message to the user
   * @return what the user entered
   */
  private String getInfo(String msg) {
    TextInputDialog td = setupTextDialog("...");
    td.setHeaderText(msg);
    td.showAndWait();
    return td.getResult();
  }

  /**
   * Validates that a proper time has been inputted
   *
   * @param input the time from the user
   * @return returns a bad input if invalid, otherwise returns the proper time
   */
  private String validateTime(String input) {
    int hour;
    int minute;
    try {
      hour = Integer.parseInt(input.substring(0, 2));
      minute = Integer.parseInt(input.substring(3));
    } catch (NumberFormatException e) {
      return bad_input;
    }
    if (hour <= 23 && minute <= 59 && input.charAt(2) == ':' && input.length() == 5) {
      return input;
    }
    return bad_input;
  }

  /**
   * Validates the user entered a valid duration for an event
   *
   * @param input the duration input from the user
   * @return returns a bad input if invalid, otherwise returns the proper time
   */
  private String validateDuration(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return bad_input;
    }
    if (Integer.parseInt(input) < 0) {
      return bad_input;
    }
    return input;
  }

  /**
   * Validates the user entered a valid day
   *
   * @param input the day input from the user
   * @return returns a bad input if invalid, otherwise returns the proper time
   */
  private String validateDay(String input) {
    if (input.equalsIgnoreCase(DayType.MONDAY.rep)) {
      return DayType.MONDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.TUESDAY.rep)) {
      return DayType.TUESDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.WEDNESDAY.rep)) {
      return DayType.WEDNESDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.THURSDAY.rep)) {
      return DayType.THURSDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.FRIDAY.rep)) {
      return DayType.FRIDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.SATURDAY.rep)) {
      return DayType.SATURDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.SUNDAY.rep)) {
      return DayType.SUNDAY.rep;
    }
    return bad_input;
  }

  /**
   * Sets max event limit
   */
  private void setEvents() {
    TextInputDialog td = setupTextDialog("ENTER AN INTEGER");
    td.setHeaderText("Enter Maximum Daily Events");
    td.showAndWait().ifPresent(string -> {
      try {
        int newCount = validateMaxSettings(string, maxEventCount, week.getMaxEvents(), "Events: ");
        week.setMaxEvents(newCount);
      } catch (IOException e) {
        try {
          showErrorMsg("Not a valid integer!");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  /**
   * Sets max task limit
   */
  private void setTasks() {
    TextInputDialog td = setupTextDialog("ENTER AN INTEGER");
    td.setHeaderText("Enter Maximum Daily Tasks");
    td.showAndWait().ifPresent(string -> {
      try {
        int newCount = validateMaxSettings(string, maxTaskCount, week.getMaxTasks(), "Tasks: ");
        week.setMaxTasks(newCount);
      } catch (IOException e) {
        try {
          showErrorMsg("Not a valid integer!");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  /**
   * Validates that a valid max limit was given
   *
   * @param string    The user input
   * @param label     the label associated with the max
   * @param itemCount the max number of SchedulingItems
   * @param end       "Task: " or "Event: "
   * @return the max number
   * @throws IOException a valid integer was not given
   */
  private int validateMaxSettings(String string, Label label, int itemCount, String end)
      throws IOException {
    int count = itemCount;
    try {
      count = Integer.parseInt(string);
      label.setText("Max Daily " + end + string);
    } catch (NumberFormatException exe) {
      showErrorMsg("Not a valid integer!");
    }
    return count;
  }

  /**
   * Sets up the text dialog
   *
   * @param msg the message to be displayed on the textDialog
   * @return the TextDialog
   */
  private TextInputDialog setupTextDialog(String msg) {
    Image img = new Image("calendar.png");
    ImageView image = new ImageView(img);
    image.setFitHeight(100);
    image.setFitWidth(100);
    TextInputDialog td = new TextInputDialog(msg);
    td.setGraphic(image);
    return td;
  }
}
