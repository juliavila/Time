package juliavila.time;

import java.util.Date;

/**
 * Created by julia.vila on 22/02/2016.
 */
public class Ponto {
    // Labels table name
    public static final String TABLE = "Ponto";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_dataInicial = "dataInicial";
    public static final String KEY_dataFinal = "dataFinal";

    // property help us to keep data
    public int ponto_ID;
    public Date dataInicial;
    public Date dataFinal;
}
