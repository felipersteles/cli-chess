package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033\143");
        System.out.flush();
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (background)
            System.out.print(ANSI_BLUE_BACKGROUND);

        if (piece == null) {
            if (background) {
                System.out.print(" x " + ANSI_RESET);
            } else {
                System.out.print(" - " + ANSI_RESET);
            }
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + " " + piece + " " + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + " " + piece + " " + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();

            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));

            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao estanciar posição. As possibilidades são de a1 a h8.");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println();
        System.out.println("Numero de jogadas: " + chessMatch.getTurn());

        if (!chessMatch.getCheckMate()) {
            System.out.println("Vez do time: " + ((chessMatch.getCurrentPlayer() == Color.BLACK) ? "preto" : "branco"));
            if (chessMatch.getCheck()) {
                System.out.println("CHECK!");
            }
        } else {
            System.out.println("CHECKMATE!");
            System.out.println(
                    "VENCE O TIME " + ((chessMatch.getCurrentPlayer() == Color.BLACK) ? "PRETO" : "BRANCO") + "!");
            System.out.println();
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print(ANSI_PURPLE + (8 - i) + " " + ANSI_RESET);
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println(ANSI_PURPLE + "   a   b   c   d   e   f   g   h" + ANSI_RESET);
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println(ANSI_PURPLE + "   a   b   c   d   e   f   g   h" + ANSI_RESET);
    }

    private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
        List<ChessPiece> white = capturedPieces.stream()
                .filter(capturedPiece -> capturedPiece.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = capturedPieces.stream()
                .filter(capturedPiece -> capturedPiece.getColor() == Color.BLACK).collect(Collectors.toList());

        System.out.println("Peças capturadas: ");
        System.out.print("Time branco: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Time preto: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
