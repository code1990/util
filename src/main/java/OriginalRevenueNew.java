import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OriginalRevenueNew implements Serializable {
	private static final long serialVersionUID = 5486081199748991522L;
	/*** 处理月 */
	//"month"
	private String id;
	/*** 处理月 */
	//"month"
	private Date createTime;
	/*** 处理月 */
	//"month"
	private String month;
	/*** 承运日期 */
	//"flt_date"
	private Date fltDate;
	/*** 航班号 */
	//"fltno"
	private String fltno;
	/*** 考核责任区代码 */
	//"kh_code"
	private String khCode;
	/*** 考核责任区名称 */
	//"kh_name"
	private String khName;
	/*** 销售责任区代码 */
	//"sale_code"
	private String saleCode;
	/*** 销售责任区名称 */
	//"sale_name"
	private String saleName;
	/*** 始发责任区代码 */
	//"sf_code"
	private String sfCode;
	/*** 始发责任区名称 */
	//"sf_name"
	private String sfName;
	/*** 代理人代号 */
	//"agent_code"
	private String agentCode;
	/*** 代理人名称 */
	//"agent_name"
	private String agentName;
	/*** 代理人所在城市代码 */
	//"agent_city_code"
	private String agentCityCode;
	/*** 代理人所在城市名称 */
	//"agent_city_name"
	private String agentCityName;
	/*** 票面航程 */
	//"line"
	private String line;
	/*** 票面航程国际/国内标识 */
	//"line_flag"
	private String lineFlag;
	/*** 航段起止机场代码 */
	//"segment"
	private String segment;
	/*** 航段始发机场代码 */
	//"original_city"
	private String originalCity;
	/*** 航段到达机场代码 */
	//"destination_city"
	private String destinationCity;
	/*** 航段类型 */
	//"segment_type"
	private String segmentType;
	/*** 运输子舱位 */
	//"cls"
	private String cls;
	/*** 销售子舱位 */
	//"xs_cls"
	private String xsCls;
	/*** 座位等级 */
	//"seat_type"
	private String seatType;
	/*** 航线机场代码全程 */
	//"line_code"
	private String lineCode;
	/*** 出票年月 */
	//"print_month"
	private String printMonth;
	/*** 高端舱位标识 */
	//"cls_flag"
	private String clsFlag;
	/*** 团散标识 */
	//"group_flag"
	private String groupFlag;
	
	/*** 总旅客 */
	//"passenger"
	private Integer passenger;
	/*** 销售座位等级 */
	//"sales_seat_rating"
	private String salesSeatRating;
	/*** 净收入 */
	//"income"
	private BigDecimal income;
	/*** 额外手续费 */
	//"extra_fee"
	private BigDecimal extraFee;
	/*** 燃油费 */
	//"oil"
	private BigDecimal oil;
	/*** 标准手续费 */
	//"standard_fee"
	private BigDecimal standardFee;
	/*** 国内/国际票 */
	//"ticket_flag"
	private String ticketFlag;
	/*** 分摊收入 */
	//"share_income"
	private BigDecimal shareIncome;
	/*** 同期分摊收入 *//*
	private BigDecimal lastYearShareIncome;*/
	/*** 公司代码 */
	//"company"
	private String company;
	
	//--------------------------------以上字段与OriginalRevenue一致 后面为新增
	/*** 承运年月*/
	//"flt_year_month"
	private String fltYearMonth;
	/*** 提前出票分组*/
	//"ticket_group"
	private String ticketGroup;
	/*** 始发大区代码*/
	//"sf_area_code"
	private String sfAreaCode;
	/*** 始发大区名称*/
	//"sf_area_name"
	private String sfAreaName;
	/*** 销售单位属性*/
	//"sale_company_property"
	private String saleCompanyProperty;
	/*** 销售大区代码*/
	//"sale_area_code"
	private String saleAreaCode;
	/*** 销售大区名称*/
	//"sale_area_name"
	private String saleAreaName;
	/*** 销售大区属性*/
	//"sale_area_property"
	private String saleAreaProperty;
	/*** 考核单位属性*/
	//"kpi_company_property"
	private String kpiCompanyProperty;
	/*** 考核大区代码*/
	//"kpi_area_code"
	private String kpiAreaCode;
	/*** 考核大区名称*/
	//"kpi_area_name"
	private String kpiAreaName;
	/*** 考核大区属性*/
	//"kpi_area_property"
	private String kpiAreaProperty;
	/*** null*/
	//"overseas_branch"
	private String overseasBranch;
	/*** 本异地始发标识*/
	//"is_local"
	private String isLocal;
	/*** 出票渠道*/
	//"ticket_channel"
	private String ticketChannel;
	/*** 渠道细分*/
	//"channel_partition"
	private String channelPartition;
	/*** 渠道种类*/
	//"channel_type"
	private String channelType;
	/*** 代理人所属国家代码*/
	//"agent_nation_code"
	private String agentNationCode;
	/*** 代理人所属国家*/
	//"agent_nation_name"
	private String agentNationName;
	/*** 直销/分销*/
	//"is_distribution"
	private String isDistribution;
	/*** 捆绑代理名称*/
	//"bind_agent_name"
	private String bindAgentName;
	/*** 统签标识*/
	//"unifysign_code"
	private String unifysignCode;
	/*** 统签协议*/
	//"unifysign_agreement"
	private String unifysignAgreement;
	/*** 统签协议名称*/
	//"unifysign_agreement_name"
	private String unifysignAgreementName;
	/*** 票面航程始发机场代码*/
	//"ticket_segment_arrcode"
	private String ticketSegmentArrcode;
	/*** 直达/中转标识*/
	//"transfer_code"
	private String transferCode;
	/*** 航线类型*/
	//"line_type"
	private String lineType;
	/*** 航线分区*/
	//"line_area"
	private String lineArea;
	/*** 航线单位归属*/
	//"line_company_belong"
	private String lineCompanyBelong;
	/*** 航线大区单位归属*/
	//"line_company_area_belong"
	private String lineCompanyAreaBelong;
	/*** 航段始发城市名称*/
	//"segment_sfcity_name"
	private String segmentSfcityName;
	/*** 航段到达城市名称*/
	//"segment_arrcity_name"
	private String segmentArrcityName;
	/*** 实际承运航班号*/
	//"actual_transfer_line"
	private String actualTransferLine;
	/*** 实际运价基础*/
	//"actual_trsfee_basic"
	private String actualTrsfeeBasic;
	/*** 运价折扣率分组*/
	//"actual_discount_group"
	private String actualDiscountGroup;
	/*** 运输座位等级*/
	//"transfer_seat_level"
	private String transferSeatLevel;
	/*** 长航线标识*/
	//"long_line_mark"
	private String longLineMark;
	/*** 航线代号*/
	//"line_symbol"
	private String lineSymbol;
	/*** 航线管辖责任区*/
	//"line_duty_area"
	private String lineDutyArea;
	/*** 本航/外航票*/
	//"is_local_airline"
	private String isLocalAirline;
	/*** 票证种类*/
	//"ticket_type"
	private String ticketType;
	/*** 数据来源渠道*/
	//"date_source_channel"
	private String dateSourceChannel;
	/*** 销售货币代码*/
	//"sale_currency_code"
	private String saleCurrencyCode;
	/*** 订座数据来源*/
	//"source_date_reservation"
	private String sourceDateReservation;
	/*** 政府采购标识*/
	//"is_government_procurement"
	private String isGovernmentProcurement;
	/*** 政府采购编码*/
	//"government_procurement_number"
	private String governmentProcurementNumber;
	/*** 机号*/
	//"airplane_number"
	private String airplaneNumber;
	/*** 实际承运单位*/
	//"actual_carrier_company"
	private String actualCarrierCompany;
	/*** EMD类型代码*/
	//"emd_type_code"
	private String emdTypeCode;
	/*** EMD种类代码*/
	//"emd_kind_code"
	private String emdKindCode;
	/*** EMD用途代码*/
	//"emd_use_code"
	private String emdUseCode;
	/*** 免票旅客*/
	//"free_ticket_passenger"
	private String freeTicketPassenger;
	/*** 客标准手续费*/
	//"passenger_standard_charge"
	private String passengerStandardCharge;
	/*** 航空保险费（人民币）*/
	//"airline_insurance_rmb"
	private String airlineInsuranceRmb;
	/*** 动态兑换免票还原收入*/
	//"dynamic_exchange_freeticket_income"
	private String dynamicExchangeFreeticketIncome;
	/*** 出票日期 */
	//"print_date"
	private String printDate;
	/*** 机型 */
	//"pmodel"
	private String pmodel;
	/*** 数据类型 */
	//"date_type"
	private String dateType;
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getFltDate() {
		return fltDate;
	}

	public void setFltDate(Date fltDate) {
		this.fltDate = fltDate;
	}

	public String getFltno() {
		return fltno;
	}

	public void setFltno(String fltno) {
		this.fltno = fltno;
	}

	public String getKhCode() {
		return khCode;
	}

	public void setKhCode(String khCode) {
		this.khCode = khCode;
	}

	public String getKhName() {
		return khName;
	}

	public void setKhName(String khName) {
		this.khName = khName;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getSfCode() {
		return sfCode;
	}

	public void setSfCode(String sfCode) {
		this.sfCode = sfCode;
	}

	public String getSfName() {
		return sfName;
	}

	public void setSfName(String sfName) {
		this.sfName = sfName;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCityCode() {
		return agentCityCode;
	}

	public void setAgentCityCode(String agentCityCode) {
		this.agentCityCode = agentCityCode;
	}

	public String getAgentCityName() {
		return agentCityName;
	}

	public void setAgentCityName(String agentCityName) {
		this.agentCityName = agentCityName;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLineFlag() {
		return lineFlag;
	}

	public void setLineFlag(String lineFlag) {
		this.lineFlag = lineFlag;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getOriginalCity() {
		return originalCity;
	}

	public void setOriginalCity(String originalCity) {
		this.originalCity = originalCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(String segmentType) {
		this.segmentType = segmentType;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getXsCls() {
		return xsCls;
	}

	public void setXsCls(String xsCls) {
		this.xsCls = xsCls;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getPrintMonth() {
		return printMonth;
	}

	public void setPrintMonth(String printMonth) {
		this.printMonth = printMonth;
	}

	public String getClsFlag() {
		return clsFlag;
	}

	public void setClsFlag(String clsFlag) {
		this.clsFlag = clsFlag;
	}
	
	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public String getSalesSeatRating() {
		return salesSeatRating;
	}

	public void setSalesSeatRating(String salesSeatRating) {
		this.salesSeatRating = salesSeatRating;
	}

	public Integer getPassenger() {
		return passenger;
	}

	public void setPassenger(Integer passenger) {
		this.passenger = passenger;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(BigDecimal extraFee) {
		this.extraFee = extraFee;
	}

	public BigDecimal getOil() {
		return oil;
	}

	public void setOil(BigDecimal oil) {
		this.oil = oil;
	}

	public BigDecimal getStandardFee() {
		return standardFee;
	}

	public void setStandardFee(BigDecimal standardFee) {
		this.standardFee = standardFee;
	}

	public String getTicketFlag() {
		return ticketFlag;
	}

	public void setTicketFlag(String ticketFlag) {
		this.ticketFlag = ticketFlag;
	}

	public BigDecimal getShareIncome() {
		return shareIncome;
	}

	public void setShareIncome(BigDecimal shareIncome) {
		this.shareIncome = shareIncome;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFltYearMonth() {
		return fltYearMonth;
	}

	public void setFltYearMonth(String fltYearMonth) {
		this.fltYearMonth = fltYearMonth;
	}

	public String getTicketGroup() {
		return ticketGroup;
	}

	public void setTicketGroup(String ticketGroup) {
		this.ticketGroup = ticketGroup;
	}

	public String getSfAreaCode() {
		return sfAreaCode;
	}

	public void setSfAreaCode(String sfAreaCode) {
		this.sfAreaCode = sfAreaCode;
	}

	public String getSfAreaName() {
		return sfAreaName;
	}

	public void setSfAreaName(String sfAreaName) {
		this.sfAreaName = sfAreaName;
	}

	public String getSaleCompanyProperty() {
		return saleCompanyProperty;
	}

	public void setSaleCompanyProperty(String saleCompanyProperty) {
		this.saleCompanyProperty = saleCompanyProperty;
	}

	public String getSaleAreaCode() {
		return saleAreaCode;
	}

	public void setSaleAreaCode(String saleAreaCode) {
		this.saleAreaCode = saleAreaCode;
	}

	public String getSaleAreaName() {
		return saleAreaName;
	}

	public void setSaleAreaName(String saleAreaName) {
		this.saleAreaName = saleAreaName;
	}

	public String getSaleAreaProperty() {
		return saleAreaProperty;
	}

	public void setSaleAreaProperty(String saleAreaProperty) {
		this.saleAreaProperty = saleAreaProperty;
	}

	public String getKpiCompanyProperty() {
		return kpiCompanyProperty;
	}

	public void setKpiCompanyProperty(String kpiCompanyProperty) {
		this.kpiCompanyProperty = kpiCompanyProperty;
	}

	public String getKpiAreaCode() {
		return kpiAreaCode;
	}

	public void setKpiAreaCode(String kpiAreaCode) {
		this.kpiAreaCode = kpiAreaCode;
	}

	public String getKpiAreaName() {
		return kpiAreaName;
	}

	public void setKpiAreaName(String kpiAreaName) {
		this.kpiAreaName = kpiAreaName;
	}

	public String getKpiAreaProperty() {
		return kpiAreaProperty;
	}

	public void setKpiAreaProperty(String kpiAreaProperty) {
		this.kpiAreaProperty = kpiAreaProperty;
	}

	public String getOverseasBranch() {
		return overseasBranch;
	}

	public void setOverseasBranch(String overseasBranch) {
		this.overseasBranch = overseasBranch;
	}

	public String getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}

	public String getTicketChannel() {
		return ticketChannel;
	}

	public void setTicketChannel(String ticketChannel) {
		this.ticketChannel = ticketChannel;
	}

	public String getChannelPartition() {
		return channelPartition;
	}

	public void setChannelPartition(String channelPartition) {
		this.channelPartition = channelPartition;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getAgentNationCode() {
		return agentNationCode;
	}

	public void setAgentNationCode(String agentNationCode) {
		this.agentNationCode = agentNationCode;
	}

	public String getAgentNationName() {
		return agentNationName;
	}

	public void setAgentNationName(String agentNationName) {
		this.agentNationName = agentNationName;
	}

	public String getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(String isDistribution) {
		this.isDistribution = isDistribution;
	}

	public String getBindAgentName() {
		return bindAgentName;
	}

	public void setBindAgentName(String bindAgentName) {
		this.bindAgentName = bindAgentName;
	}

	public String getUnifysignCode() {
		return unifysignCode;
	}

	public void setUnifysignCode(String unifysignCode) {
		this.unifysignCode = unifysignCode;
	}

	public String getUnifysignAgreement() {
		return unifysignAgreement;
	}

	public void setUnifysignAgreement(String unifysignAgreement) {
		this.unifysignAgreement = unifysignAgreement;
	}

	public String getUnifysignAgreementName() {
		return unifysignAgreementName;
	}

	public void setUnifysignAgreementName(String unifysignAgreementName) {
		this.unifysignAgreementName = unifysignAgreementName;
	}

	public String getTicketSegmentArrcode() {
		return ticketSegmentArrcode;
	}

	public void setTicketSegmentArrcode(String ticketSegmentArrcode) {
		this.ticketSegmentArrcode = ticketSegmentArrcode;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getLineArea() {
		return lineArea;
	}

	public void setLineArea(String lineArea) {
		this.lineArea = lineArea;
	}

	public String getLineCompanyBelong() {
		return lineCompanyBelong;
	}

	public void setLineCompanyBelong(String lineCompanyBelong) {
		this.lineCompanyBelong = lineCompanyBelong;
	}

	public String getLineCompanyAreaBelong() {
		return lineCompanyAreaBelong;
	}

	public void setLineCompanyAreaBelong(String lineCompanyAreaBelong) {
		this.lineCompanyAreaBelong = lineCompanyAreaBelong;
	}

	public String getSegmentSfcityName() {
		return segmentSfcityName;
	}

	public void setSegmentSfcityName(String segmentSfcityName) {
		this.segmentSfcityName = segmentSfcityName;
	}

	public String getSegmentArrcityName() {
		return segmentArrcityName;
	}

	public void setSegmentArrcityName(String segmentArrcityName) {
		this.segmentArrcityName = segmentArrcityName;
	}

	public String getActualTransferLine() {
		return actualTransferLine;
	}

	public void setActualTransferLine(String actualTransferLine) {
		this.actualTransferLine = actualTransferLine;
	}

	public String getActualTrsfeeBasic() {
		return actualTrsfeeBasic;
	}

	public void setActualTrsfeeBasic(String actualTrsfeeBasic) {
		this.actualTrsfeeBasic = actualTrsfeeBasic;
	}

	public String getActualDiscountGroup() {
		return actualDiscountGroup;
	}

	public void setActualDiscountGroup(String actualDiscountGroup) {
		this.actualDiscountGroup = actualDiscountGroup;
	}

	public String getTransferSeatLevel() {
		return transferSeatLevel;
	}

	public void setTransferSeatLevel(String transferSeatLevel) {
		this.transferSeatLevel = transferSeatLevel;
	}

	public String getLongLineMark() {
		return longLineMark;
	}

	public void setLongLineMark(String longLineMark) {
		this.longLineMark = longLineMark;
	}

	public String getLineSymbol() {
		return lineSymbol;
	}

	public void setLineSymbol(String lineSymbol) {
		this.lineSymbol = lineSymbol;
	}

	public String getLineDutyArea() {
		return lineDutyArea;
	}

	public void setLineDutyArea(String lineDutyArea) {
		this.lineDutyArea = lineDutyArea;
	}

	public String getIsLocalAirline() {
		return isLocalAirline;
	}

	public void setIsLocalAirline(String isLocalAirline) {
		this.isLocalAirline = isLocalAirline;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getDateSourceChannel() {
		return dateSourceChannel;
	}

	public void setDateSourceChannel(String dateSourceChannel) {
		this.dateSourceChannel = dateSourceChannel;
	}

	public String getSaleCurrencyCode() {
		return saleCurrencyCode;
	}

	public void setSaleCurrencyCode(String saleCurrencyCode) {
		this.saleCurrencyCode = saleCurrencyCode;
	}

	public String getSourceDateReservation() {
		return sourceDateReservation;
	}

	public void setSourceDateReservation(String sourceDateReservation) {
		this.sourceDateReservation = sourceDateReservation;
	}

	public String getIsGovernmentProcurement() {
		return isGovernmentProcurement;
	}

	public void setIsGovernmentProcurement(String isGovernmentProcurement) {
		this.isGovernmentProcurement = isGovernmentProcurement;
	}

	public String getGovernmentProcurementNumber() {
		return governmentProcurementNumber;
	}

	public void setGovernmentProcurementNumber(String governmentProcurementNumber) {
		this.governmentProcurementNumber = governmentProcurementNumber;
	}

	public String getAirplaneNumber() {
		return airplaneNumber;
	}

	public void setAirplaneNumber(String airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
	}

	public String getActualCarrierCompany() {
		return actualCarrierCompany;
	}

	public void setActualCarrierCompany(String actualCarrierCompany) {
		this.actualCarrierCompany = actualCarrierCompany;
	}

	public String getEmdTypeCode() {
		return emdTypeCode;
	}

	public void setEmdTypeCode(String emdTypeCode) {
		this.emdTypeCode = emdTypeCode;
	}

	public String getEmdKindCode() {
		return emdKindCode;
	}

	public void setEmdKindCode(String emdKindCode) {
		this.emdKindCode = emdKindCode;
	}

	public String getEmdUseCode() {
		return emdUseCode;
	}

	public void setEmdUseCode(String emdUseCode) {
		this.emdUseCode = emdUseCode;
	}

	public String getFreeTicketPassenger() {
		return freeTicketPassenger;
	}

	public void setFreeTicketPassenger(String freeTicketPassenger) {
		this.freeTicketPassenger = freeTicketPassenger;
	}

	public String getPassengerStandardCharge() {
		return passengerStandardCharge;
	}

	public void setPassengerStandardCharge(String passengerStandardCharge) {
		this.passengerStandardCharge = passengerStandardCharge;
	}

	public String getAirlineInsuranceRmb() {
		return airlineInsuranceRmb;
	}

	public void setAirlineInsuranceRmb(String airlineInsuranceRmb) {
		this.airlineInsuranceRmb = airlineInsuranceRmb;
	}

	public String getDynamicExchangeFreeticketIncome() {
		return dynamicExchangeFreeticketIncome;
	}

	public void setDynamicExchangeFreeticketIncome(
			String dynamicExchangeFreeticketIncome) {
		this.dynamicExchangeFreeticketIncome = dynamicExchangeFreeticketIncome;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getPmodel() {
		return pmodel;
	}

	public void setPmodel(String pmodel) {
		this.pmodel = pmodel;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	@Override
	public String toString() {
		return "OriginalRevenueNew [month=" + month + ", fltDate=" + fltDate
				+ ", fltno=" + fltno + ", khCode=" + khCode + ", khName="
				+ khName + ", saleCode=" + saleCode + ", saleName=" + saleName
				+ ", sfCode=" + sfCode + ", sfName=" + sfName + ", agentCode="
				+ agentCode + ", agentName=" + agentName + ", agentCityCode="
				+ agentCityCode + ", agentCityName=" + agentCityName
				+ ", line=" + line + ", lineFlag=" + lineFlag + ", segment="
				+ segment + ", originalCity=" + originalCity
				+ ", destinationCity=" + destinationCity + ", segmentType="
				+ segmentType + ", cls=" + cls + ", xsCls=" + xsCls
				+ ", seatType=" + seatType + ", lineCode=" + lineCode
				+ ", printMonth=" + printMonth + ", clsFlag=" + clsFlag
				+ ", groupFlag=" + groupFlag + ", passenger=" + passenger
				+ ", salesSeatRating=" + salesSeatRating + ", income=" + income
				+ ", extraFee=" + extraFee + ", oil=" + oil + ", standardFee="
				+ standardFee + ", ticketFlag=" + ticketFlag + ", shareIncome="
				+ shareIncome + ", company=" + company + ", fltYearMonth="
				+ fltYearMonth + ", ticketGroup=" + ticketGroup
				+ ", sfAreaCode=" + sfAreaCode + ", sfAreaName=" + sfAreaName
				+ ", saleCompanyProperty=" + saleCompanyProperty
				+ ", saleAreaCode=" + saleAreaCode + ", saleAreaName="
				+ saleAreaName + ", saleAreaProperty=" + saleAreaProperty
				+ ", kpiCompanyProperty=" + kpiCompanyProperty
				+ ", kpiAreaCode=" + kpiAreaCode + ", kpiAreaName="
				+ kpiAreaName + ", kpiAreaProperty=" + kpiAreaProperty
				+ ", overseasBranch=" + overseasBranch + ", isLocal=" + isLocal
				+ ", ticketChannel=" + ticketChannel + ", channelPartition="
				+ channelPartition + ", channelType=" + channelType
				+ ", agentNationCode=" + agentNationCode + ", agentNationName="
				+ agentNationName + ", isDistribution=" + isDistribution
				+ ", bindAgentName=" + bindAgentName + ", unifysignCode="
				+ unifysignCode + ", unifysignAgreement=" + unifysignAgreement
				+ ", unifysignAgreementName=" + unifysignAgreementName
				+ ", ticketSegmentArrcode=" + ticketSegmentArrcode
				+ ", transferCode=" + transferCode + ", lineType=" + lineType
				+ ", lineArea=" + lineArea + ", lineCompanyBelong="
				+ lineCompanyBelong + ", lineCompanyAreaBelong="
				+ lineCompanyAreaBelong + ", segmentSfcityName="
				+ segmentSfcityName + ", segmentArrcityName="
				+ segmentArrcityName + ", actualTransferLine="
				+ actualTransferLine + ", actualTrsfeeBasic="
				+ actualTrsfeeBasic + ", actualDiscountGroup="
				+ actualDiscountGroup + ", transferSeatLevel="
				+ transferSeatLevel + ", longLineMark=" + longLineMark
				+ ", lineSymbol=" + lineSymbol + ", lineDutyArea="
				+ lineDutyArea + ", isLocalAirline=" + isLocalAirline
				+ ", ticketType=" + ticketType + ", dateSourceChannel="
				+ dateSourceChannel + ", saleCurrencyCode=" + saleCurrencyCode
				+ ", sourceDateReservation=" + sourceDateReservation
				+ ", isGovernmentProcurement=" + isGovernmentProcurement
				+ ", governmentProcurementNumber="
				+ governmentProcurementNumber + ", airplaneNumber="
				+ airplaneNumber + ", actualCarrierCompany="
				+ actualCarrierCompany + ", emdTypeCode=" + emdTypeCode
				+ ", emdKindCode=" + emdKindCode + ", emdUseCode=" + emdUseCode
				+ ", freeTicketPassenger=" + freeTicketPassenger
				+ ", passengerStandardCharge=" + passengerStandardCharge
				+ ", airlineInsuranceRmb=" + airlineInsuranceRmb
				+ ", dynamicExchangeFreeticketIncome="
				+ dynamicExchangeFreeticketIncome + ", printDate=" + printDate
				+ ", pmodel=" + pmodel + ", dateType=" + dateType + "]";
	}
	
}
