package tom.study.common.feign.resp;

import lombok.Getter;

@Getter
public class LightFeignMetaResponse {

    String dataId; // 데이터ID
    String itstId; // 교차로ID
    String eqmnId; // 장비이름
    String regDt; // 등록일시

    String ntLtsgRmdrCs; // 북쪽좌회전잔여시간
    String ntPdsgRmdrCs; // 북쪽보행잔여시간
    String ntStsgRmdrCs; // 북쪽직진잔여시간
    String ntUtsgRmdrCs; // 북쪽유턴잔여시간


    String etLtsgRmdrCs; // 동쪽좌회전잔여시간
    String etPdsgRmdrCs; // 동쪽보행잔여시간
    String etStsgRmdrCs; // 동쪽직진잔여시간
    String etUtsgRmdrCs; // 동쪽유턴잔여시간


    String stLtsgRmdrCs; // 남쪽좌회전잔여시간
    String stPdsgRmdrCs; // 남쪽보행잔여시간
    String stStsgRmdrCs; // 남쪽직진잔여시간
    String stUtsgRmdrCs; // 남쪽유턴잔여시간


    String wtLtsgRmdrCs; // 서쪽좌회전잔여시간
    String wtPdsgRmdrCs; // 서쪽보행잔여시간
    String wtStsgRmdrCs; // 서쪽직진잔여시간
    String wtUtsgRmdrCs; // 서쪽유턴잔여시간
}
