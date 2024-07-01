package com.wtf2.erp.company.dto.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기업개요 조회 시 사용하는 검색어
 * crno: 법인 등록번호
 * corpNm: 법인 명칭
 */
@Data
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    /** name */
    private String corpNm;
    /** guid */
    private String crno;
    /** 대표 (CEO) */
    private String enpRprFnm;
    private String corpEnsnNm;
    private String enpPbanCmpyNm;
    private String corpRegMrktDcd;
    private String corpRegMrktDcdNm;
    private String corpDcd;
    private String corpDcdNm;
    private String bzno;
    private String enpOzpno;
    private String enpBsadr;
    private String enpDtadr;
    private String enpHmpgUrl;
    private String enpTlno;
    private String enpFxno;
    private String sicNm;
    private String enpEstbDt;
    private String enpStacMm;
    private String enpXchgLstgDt;
    private String enpXchgLstgAbolDt;
    private String enpKosdaqLstgDt;
    private String enpKosdaqLstgAbolDt;
    private String enpKrxLstgDt;
    private String enpKrxLstgAbolDt;
    private String smenpYn;
    private String enpMntrBnkNm;
    private String enpEmpeCnt;
    private String empeAvgCnwkTermCtt;
    private String enpPn1AvgSlryAmt;
    private String actnAudpnNm;
    private String audtRptOpnnCtt;
    private String enpMainBizNm;
    private String fssCorpUnqNo;
    private String fssCorpChgDtm;
    private String fstOpegDt;
    private String lastOpegDt;

    public CompanyDto(Long id, String corpNm, String crno, String enpRprFnm) {
        this.id = id;
        this.corpNm = corpNm;
        this.crno = crno;
        this.enpRprFnm = enpRprFnm;
    }
}
