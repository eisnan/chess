package chess.mapper;

import chess.domain.ChessBoard;
import chess.domain.File;
import chess.domain.Piece;
import chess.domain.Rank;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ChessboardHtmlMapper {
    public String toPage(ChessBoard chessBoard) {

        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <style>" +
                "        .chess-board { border-spacing: 0; border-collapse: collapse; }\n" +
                "        .chess-board th { padding: .5em; }\n" +
                "        .chess-board td { border: 1px solid; width: 2em; height: 2em; }\n" +
                "        .chess-board .light { background: #eee; }\n" +
                "        .chess-board .dark { background: #999; }    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table class=\"chess-board\">\n" +
                "    <tbody><tr>\n" +
                "        <th></th>\n" +
                "        <th>a</th>\n" +
                "        <th>b</th>\n" +
                "        <th>c</th>\n" +
                "        <th>d</th>\n" +
                "        <th>e</th>\n" +
                "        <th>f</th>\n" +
                "        <th>g</th>\n" +
                "        <th>h</th>\n" +
                "    </tr>\n";
        Piece[][] arrayModel = chessBoard.getArrayModel();

        boolean white = true;

        for (int j = Rank.values().length - 1; j >= 0; j--) {
            html += "<tr><th>" + (j + 1) + "</th>";
            for (int i = 0; i < File.values().length; i++) {
                html += "<td class=\"" + (white ? "light" : "dark") + "\">" + ((arrayModel[i][j] != null) ? arrayModel[i][j].getUnicode() : "") + "</td>\n";
                white = !white;
            }
            white = !white;
            html += "</tr>";
        }

        System.out.println(Arrays.toString(arrayModel));

        html += "</tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
