package com.example.kamil.e_pillbox.DataAccess.tabela;

public interface Lek_interface {
    String TABLE_NAME="lekarstwa";

    interface Columns{
        String LEK_ID="_id";
        String LEK_NAZWA="nazwa_leku";
        String LEK_ILOSC="ilosc_w_opakowaniu";
        String LEK_DATA="data_waznosci";
    }
}
