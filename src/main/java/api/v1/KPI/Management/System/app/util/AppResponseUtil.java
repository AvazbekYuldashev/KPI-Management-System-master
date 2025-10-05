package api.v1.KPI.Management.System.app.util;

import api.v1.KPI.Management.System.app.dto.AppResponse;

public class AppResponseUtil {

    public static AppResponse<String> chek(boolean b) {
        if (b){
            return new AppResponse<>("Success");
        } else {
            return new AppResponse<>("Filed");
        }

    }
}
