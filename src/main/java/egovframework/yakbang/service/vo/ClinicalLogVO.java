package egovframework.yakbang.service.vo;

import java.util.Date;
import lombok.Data;

/**
 * 임상 로그 VO
 */
@Data
public class ClinicalLogVO {

    /** 로그 ID (PK) */
    private int logId;

    /** 사용자 ID */
    private int userId;

    /** 디바이스 ID */
    private String deviceId;

    /** 혈당 수치 */
    private double glucoseLevel;

    /** 마지막 주입량, 시간, 상태 */
    private double lastDoseAmount;
    private Date lastDoseTime;
    private String injectionStatus;
}