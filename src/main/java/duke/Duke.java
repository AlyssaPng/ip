package duke;

import duke.exception.DukeException;

/**
 * duke.Duke is a class in-charge of task management.
 * It allows users to add, delete, mark, unmark, specify, and list tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for duke.Duke class
     *
     * @param filePath The filepath where task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke.Duke application
     */
    public void run() {
        ui.showWelcome();
        boolean isDone = true;
        while (isDone) {
            String userInput = ui.readCommand();
            try {
                isDone = Parser.parseCommand(userInput, this.tasks, this.ui);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * The main function of the duke.Duke application.
     *
     * @param args Command line arguments
     * @throws DukeException If an error has occurred in the duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke("C:/repos/ip/src/main/java/data/duke.txt").run();
    }
}
