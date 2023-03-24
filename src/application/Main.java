package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.ChessPiece;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                System.out.println("Desenvolvido por Felipe Teles ;)");
                System.out.println("See my GitHub page: ");
                System.out.println("https://github.com/felipersteles");
                System.out.println();
                UI.printMatch(chessMatch, capturedPieces);
                System.out.println();
                System.out.print("Selecione uma peça: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                System.out.println("Peças possiveis: ");
                System.out.println();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();

                System.out.println();
                System.out.print("Diga o destino da peça: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if(capturedPiece != null){
                    capturedPieces.add(capturedPiece);
                }
            } catch (ChessException e) {
                System.out.print(e.getMessage() + " Press enter to exit.");
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage() + " Press enter to exit.");
                sc.nextLine();
            }
        }
    
        UI.clearScreen();
        UI.printMatch(chessMatch, capturedPieces);  
    }
}