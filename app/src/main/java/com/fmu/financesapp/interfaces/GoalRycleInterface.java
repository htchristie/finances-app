package com.fmu.financesapp.interfaces;

import java.util.HashMap;
import java.util.Map;

public interface GoalRycleInterface {
    Map<String, String> ICONMAP = new HashMap<String, String>() {{
        put("Alimentação", "ic_food");
        put("Educação", "ic_education");
        put("Mercado", "ic_market");
        put("Saúde", "ic_health");
        put("Transporte", "ic_transport");
        put("Outros", "ic_other");
    }};
    void onItemClick(int position );
}
