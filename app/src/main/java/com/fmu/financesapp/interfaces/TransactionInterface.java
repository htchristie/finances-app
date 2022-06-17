package com.fmu.financesapp.interfaces;

import java.util.HashMap;
import java.util.Map;

public interface TransactionInterface {
    Map<String, String> ICONMAP = new HashMap<String, String>() {{
        put("Salário", "ic_credit_card");
        put("Alimentação", "ic_food_outline");
        put("Educação", "ic_education_outline");
        put("Mercado", "ic_market_outline");
        put("Saúde", "ic_health_outline");
        put("Transporte", "ic_transport_outline");
        put("Outros", "ic_other_outline");
    }};
    void onItemClick(int position );
}
