package quizkampen.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket  = new ServerSocket(1337);
        System.out.println("Server started.");
        try {
            while (true) {
                Socket clientOne = serverSocket.accept();
                System.out.println(clientOne + " has connected.");

                Socket clientTwo = serverSocket.accept();
                System.out.println(clientTwo + " has connected.");

                System.out.println("Assigning IO streams.");
                ClientConnection clientConnectionOne = new ClientConnection(clientOne); // här ansluter
                ClientConnection clientConnectionTwo = new ClientConnection(clientTwo); // här me

                System.out.println("Starting client-handler thread.");
                Thread clientHandler = new Thread(new ClientHandler(clientConnectionOne, clientConnectionTwo));
                clientHandler.start();
            }
        } finally {
            serverSocket.close();
        }

    }

    public static void main(String[] args) throws IOException {
        new Server();
    }


    /*
    *
    *  public void play() throws Exception {
        String response;
        char mark = 'S';
        char opponentMark = 'P';
        try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                mark = response.charAt(8);
                opponentMark = (mark == 'X' ? 'O' : 'X');
                frame.setTitle("Tic Tac Toe - Player " + mark);
            }
            while (true) {
                response = in.readLine();
                if (response.startsWith("VALID_MOVE")) {
                    messageLabel.setText("Valid move, please wait");
                    currentSquare.setText(String.valueOf(mark));
                    currentSquare.repaint();
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    int loc = Integer.parseInt(response.substring(15));
                    board[loc].setText(String.valueOf(opponentMark));
                    board[loc].repaint();
                    messageLabel.setText("Opponent moved, your turn");
                } else if (response.startsWith("VICTORY")) {
                    messageLabel.setText("You win");
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    messageLabel.setText("You lose");
                    break;
                } else if (response.startsWith("TIE")) {
                    messageLabel.setText("You tied");
                    break;
                } else if (response.startsWith("MESSAGE")) {
                    messageLabel.setText(response.substring(8));
                }
            }
            out.println("QUIT");
     * */

}
