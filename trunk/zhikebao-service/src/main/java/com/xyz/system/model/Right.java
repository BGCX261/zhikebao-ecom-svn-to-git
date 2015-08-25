package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 权限
 */
public class Right implements java.io.Serializable {

	private int recId;
	private String groupName;
	private String shop;
	private String welcomeStyle;
	private Boolean cgPurchaseReg;
	private Boolean cgPurchaseCharge;
	private Boolean cgPurchaseStockIn;
	private Boolean cgPurchaseReturn;
	private Boolean cgPurchaseSh;
	private Boolean cgPurchaseReturnSh;
	private Boolean xsTradeReg;
	private Boolean xsPos;
	private Boolean xsTradeCancel;
	private Boolean xsTradeEdit;
	private Boolean xsTradeConfirm;
	private Boolean xsTradeCharge;
	private Boolean xsTradeChargeCancel;
	private Boolean xsTradeStockOut;
	private Boolean xsTradeStockOutCancel;
	private Boolean xsPrintSndBill;
	private Boolean xsPrintExpress;
	private Boolean xsStockOutChk;
	private Boolean xsTradeClose;
	private Boolean xsBargain;
	private Boolean xsTradeSh;
	private Boolean xsUnTradeCancel;
	private Boolean xsUnTradeClose;
	private Boolean xsOutPutExcel;
	private Boolean xsOtherPrint;
	private Boolean xsEditOperator;
	private Boolean xsEditBaseInfo;
	private Boolean xsEditCustomerInfo;
	private Boolean xsEditChargeInfo;
	private Boolean hpEditGoods;
	private Boolean hpGoodsSh;
	private Boolean hpDelGoods;
	private Boolean hpEditGoodsChk;
	private Boolean hpCreateGoodsPic;
	private Boolean hpEditGoodsPic;
	private Boolean hpEditGoodsPicChk;
	private Boolean hpGoodsInfoExcel;
	private Boolean kcStockIn;
	private Boolean kcStockInChk;
	private Boolean kcStockInSh;
	private Boolean kcStockOut;
	private Boolean kcStockOutChk;
	private Boolean kcStockOutSh;
	private Boolean kcStockCheck;
	private Boolean kcStockCheckSh;
	private Boolean kcStockInDel;
	private Boolean kcStockOutDel;
	private Boolean khEditCustomerInfo;
	private Boolean khCustomerDel;
	private Boolean khCustomerSh;
	private Boolean khCustomerExcel;
	private Boolean khEditProviderInfo;
	private Boolean khProviderDel;
	private Boolean khProviderSh;
	private Boolean khProviderExcel;
	private Boolean khEditEnemyInfo;
	private Boolean khEnemyDel;
	private Boolean khEnemySh;
	private Boolean khEnemyExcel;
	private Boolean khCustomerScore;
	private Boolean khCustomerPredeposit;
	private Boolean zkNewInOut;
	private Boolean zkInOutChk;
	private Boolean zkBank;
	private Boolean zkBankExcel;
	private Boolean zkInOutEdit;
	private Boolean zkInOutDel;
	private Boolean zkMoneyTraceEdit;
	private Boolean zkMoneyTraceDel;
	private Boolean zkMoneyTraceCharge;
	private Boolean zkMoneyTraceCancel;
	private Boolean zkPostageCharge;
	private Boolean zkPostageSh;
	private Boolean zkRevenueEdit;
	private Boolean zkRevenueConfirm;
	private Boolean zkRevenueDel;
	private Boolean zkRevenueSh;
	private Boolean zkCostEdit;
	private Boolean zkCostSh;
	private Boolean zkToday;
	private Boolean shExchangeNew;
	private Boolean shExchangeIn;
	private Boolean shExchangeOut;
	private Boolean shExchangeCharge;
	private Boolean shExchangeEdit;
	private Boolean shExchangeSh;
	private Boolean shVist;
	private Boolean shVistSh;
	private Boolean shRepairReg;
	private Boolean shRepairDo;
	private Boolean shRepairCharge;
	private Boolean shRepairSnd;
	private Boolean shRepairSh;
	private Boolean shMsc;
	private Boolean xtBackup;
	private Boolean xtExcel;
	private Boolean xtBaseInfoEdit;
	private Boolean xtBaseInfoView;
	private Boolean xtEmailSet;
	private Boolean xtEmailView;
	private Boolean xtCustom;
	private Boolean FCostPrice;
	private Boolean FProfit;
	private Boolean FProvider;
	private Boolean FTask;
	private Boolean FDepreciate;
	private Boolean bgSndNotice;
	private Boolean bgTelReg;
	private Boolean bgTelSh;
	private Boolean bgCaseDo;
	private Boolean bgCaseChk;
	private Boolean bgSalary;
	private Boolean bgNoticeView;
	private Boolean bgNoticeEdit;
	private Boolean tjSummary;
	private Boolean tjSell;
	private Boolean tjPurchase;
	private Boolean tjStock;
	private Boolean tjService;
	private Boolean tjCustomer;
	private Boolean tjSnd;
	private Boolean tjMoney;
	private Boolean tjEmployee;
	private Boolean tjAnalyse;
	private Boolean hideSys;
	private Boolean hideBaseInfo;
	private Boolean hideGoods;
	private Boolean hidePurchase;
	private Boolean hideSell;
	private Boolean hideStock;
	private Boolean hideCustomer;
	private Boolean hideBank;
	private Boolean hideService;
	private Boolean hideMail;
	private Boolean hideOa;
	private Boolean hideSta;
	private Boolean hideAna;
	private Boolean hideCustom;
	private Boolean hideTools;
	private Boolean hideHelp;
	private Boolean hideToolsBar;
	private Boolean zkMoneyTraceView;
	private Boolean cgPurchaseReturnDel;
	private Boolean cgPurchaseDel;
	private Boolean xsTradeDel;
	private Boolean xsBargainSh;
	private Boolean kcStockSh;
	private Boolean xsCloseTrace;
	private Boolean bgCaseReg;
	private Boolean bgCaseSh;
	private Boolean zkCodcharge;
	private Boolean zkCodedit;
	private Boolean zkCodsh;
	private Boolean cgPurchaseChk;
	private Boolean cgPurchaseDelCharge;
	private Boolean cgPurchaseBack;
	private Boolean cgPurchaseStop;

	public Right() {
	}

	public Right(int recId) {
		this.recId = recId;
	}

	public Right(int recId, String groupName, String shop,
			String welcomeStyle, Boolean cgPurchaseReg,
			Boolean cgPurchaseCharge, Boolean cgPurchaseStockIn,
			Boolean cgPurchaseReturn, Boolean cgPurchaseSh,
			Boolean cgPurchaseReturnSh, Boolean xsTradeReg, Boolean xsPos,
			Boolean xsTradeCancel, Boolean xsTradeEdit, Boolean xsTradeConfirm,
			Boolean xsTradeCharge, Boolean xsTradeChargeCancel,
			Boolean xsTradeStockOut, Boolean xsTradeStockOutCancel,
			Boolean xsPrintSndBill, Boolean xsPrintExpress,
			Boolean xsStockOutChk, Boolean xsTradeClose, Boolean xsBargain,
			Boolean xsTradeSh, Boolean xsUnTradeCancel, Boolean xsUnTradeClose,
			Boolean xsOutPutExcel, Boolean xsOtherPrint,
			Boolean xsEditOperator, Boolean xsEditBaseInfo,
			Boolean xsEditCustomerInfo, Boolean xsEditChargeInfo,
			Boolean hpEditGoods, Boolean hpGoodsSh, Boolean hpDelGoods,
			Boolean hpEditGoodsChk, Boolean hpCreateGoodsPic,
			Boolean hpEditGoodsPic, Boolean hpEditGoodsPicChk,
			Boolean hpGoodsInfoExcel, Boolean kcStockIn, Boolean kcStockInChk,
			Boolean kcStockInSh, Boolean kcStockOut, Boolean kcStockOutChk,
			Boolean kcStockOutSh, Boolean kcStockCheck, Boolean kcStockCheckSh,
			Boolean kcStockInDel, Boolean kcStockOutDel,
			Boolean khEditCustomerInfo, Boolean khCustomerDel,
			Boolean khCustomerSh, Boolean khCustomerExcel,
			Boolean khEditProviderInfo, Boolean khProviderDel,
			Boolean khProviderSh, Boolean khProviderExcel,
			Boolean khEditEnemyInfo, Boolean khEnemyDel, Boolean khEnemySh,
			Boolean khEnemyExcel, Boolean khCustomerScore,
			Boolean khCustomerPredeposit, Boolean zkNewInOut,
			Boolean zkInOutChk, Boolean zkBank, Boolean zkBankExcel,
			Boolean zkInOutEdit, Boolean zkInOutDel, Boolean zkMoneyTraceEdit,
			Boolean zkMoneyTraceDel, Boolean zkMoneyTraceCharge,
			Boolean zkMoneyTraceCancel, Boolean zkPostageCharge,
			Boolean zkPostageSh, Boolean zkRevenueEdit,
			Boolean zkRevenueConfirm, Boolean zkRevenueDel,
			Boolean zkRevenueSh, Boolean zkCostEdit, Boolean zkCostSh,
			Boolean zkToday, Boolean shExchangeNew, Boolean shExchangeIn,
			Boolean shExchangeOut, Boolean shExchangeCharge,
			Boolean shExchangeEdit, Boolean shExchangeSh, Boolean shVist,
			Boolean shVistSh, Boolean shRepairReg, Boolean shRepairDo,
			Boolean shRepairCharge, Boolean shRepairSnd, Boolean shRepairSh,
			Boolean shMsc, Boolean xtBackup, Boolean xtExcel,
			Boolean xtBaseInfoEdit, Boolean xtBaseInfoView, Boolean xtEmailSet,
			Boolean xtEmailView, Boolean xtCustom, Boolean FCostPrice,
			Boolean FProfit, Boolean FProvider, Boolean FTask,
			Boolean FDepreciate, Boolean bgSndNotice, Boolean bgTelReg,
			Boolean bgTelSh, Boolean bgCaseDo, Boolean bgCaseChk,
			Boolean bgSalary, Boolean bgNoticeView, Boolean bgNoticeEdit,
			Boolean tjSummary, Boolean tjSell, Boolean tjPurchase,
			Boolean tjStock, Boolean tjService, Boolean tjCustomer,
			Boolean tjSnd, Boolean tjMoney, Boolean tjEmployee,
			Boolean tjAnalyse, Boolean hideSys, Boolean hideBaseInfo,
			Boolean hideGoods, Boolean hidePurchase, Boolean hideSell,
			Boolean hideStock, Boolean hideCustomer, Boolean hideBank,
			Boolean hideService, Boolean hideMail, Boolean hideOa,
			Boolean hideSta, Boolean hideAna, Boolean hideCustom,
			Boolean hideTools, Boolean hideHelp, Boolean hideToolsBar,
			Boolean zkMoneyTraceView, Boolean cgPurchaseReturnDel,
			Boolean cgPurchaseDel, Boolean xsTradeDel, Boolean xsBargainSh,
			Boolean kcStockSh, Boolean xsCloseTrace, Boolean bgCaseReg,
			Boolean bgCaseSh, Boolean zkCodcharge, Boolean zkCodedit,
			Boolean zkCodsh, Boolean cgPurchaseChk,
			Boolean cgPurchaseDelCharge, Boolean cgPurchaseBack,
			Boolean cgPurchaseStop) {
		this.recId = recId;
		this.groupName = groupName;
		this.shop = shop;
		this.welcomeStyle = welcomeStyle;
		this.cgPurchaseReg = cgPurchaseReg;
		this.cgPurchaseCharge = cgPurchaseCharge;
		this.cgPurchaseStockIn = cgPurchaseStockIn;
		this.cgPurchaseReturn = cgPurchaseReturn;
		this.cgPurchaseSh = cgPurchaseSh;
		this.cgPurchaseReturnSh = cgPurchaseReturnSh;
		this.xsTradeReg = xsTradeReg;
		this.xsPos = xsPos;
		this.xsTradeCancel = xsTradeCancel;
		this.xsTradeEdit = xsTradeEdit;
		this.xsTradeConfirm = xsTradeConfirm;
		this.xsTradeCharge = xsTradeCharge;
		this.xsTradeChargeCancel = xsTradeChargeCancel;
		this.xsTradeStockOut = xsTradeStockOut;
		this.xsTradeStockOutCancel = xsTradeStockOutCancel;
		this.xsPrintSndBill = xsPrintSndBill;
		this.xsPrintExpress = xsPrintExpress;
		this.xsStockOutChk = xsStockOutChk;
		this.xsTradeClose = xsTradeClose;
		this.xsBargain = xsBargain;
		this.xsTradeSh = xsTradeSh;
		this.xsUnTradeCancel = xsUnTradeCancel;
		this.xsUnTradeClose = xsUnTradeClose;
		this.xsOutPutExcel = xsOutPutExcel;
		this.xsOtherPrint = xsOtherPrint;
		this.xsEditOperator = xsEditOperator;
		this.xsEditBaseInfo = xsEditBaseInfo;
		this.xsEditCustomerInfo = xsEditCustomerInfo;
		this.xsEditChargeInfo = xsEditChargeInfo;
		this.hpEditGoods = hpEditGoods;
		this.hpGoodsSh = hpGoodsSh;
		this.hpDelGoods = hpDelGoods;
		this.hpEditGoodsChk = hpEditGoodsChk;
		this.hpCreateGoodsPic = hpCreateGoodsPic;
		this.hpEditGoodsPic = hpEditGoodsPic;
		this.hpEditGoodsPicChk = hpEditGoodsPicChk;
		this.hpGoodsInfoExcel = hpGoodsInfoExcel;
		this.kcStockIn = kcStockIn;
		this.kcStockInChk = kcStockInChk;
		this.kcStockInSh = kcStockInSh;
		this.kcStockOut = kcStockOut;
		this.kcStockOutChk = kcStockOutChk;
		this.kcStockOutSh = kcStockOutSh;
		this.kcStockCheck = kcStockCheck;
		this.kcStockCheckSh = kcStockCheckSh;
		this.kcStockInDel = kcStockInDel;
		this.kcStockOutDel = kcStockOutDel;
		this.khEditCustomerInfo = khEditCustomerInfo;
		this.khCustomerDel = khCustomerDel;
		this.khCustomerSh = khCustomerSh;
		this.khCustomerExcel = khCustomerExcel;
		this.khEditProviderInfo = khEditProviderInfo;
		this.khProviderDel = khProviderDel;
		this.khProviderSh = khProviderSh;
		this.khProviderExcel = khProviderExcel;
		this.khEditEnemyInfo = khEditEnemyInfo;
		this.khEnemyDel = khEnemyDel;
		this.khEnemySh = khEnemySh;
		this.khEnemyExcel = khEnemyExcel;
		this.khCustomerScore = khCustomerScore;
		this.khCustomerPredeposit = khCustomerPredeposit;
		this.zkNewInOut = zkNewInOut;
		this.zkInOutChk = zkInOutChk;
		this.zkBank = zkBank;
		this.zkBankExcel = zkBankExcel;
		this.zkInOutEdit = zkInOutEdit;
		this.zkInOutDel = zkInOutDel;
		this.zkMoneyTraceEdit = zkMoneyTraceEdit;
		this.zkMoneyTraceDel = zkMoneyTraceDel;
		this.zkMoneyTraceCharge = zkMoneyTraceCharge;
		this.zkMoneyTraceCancel = zkMoneyTraceCancel;
		this.zkPostageCharge = zkPostageCharge;
		this.zkPostageSh = zkPostageSh;
		this.zkRevenueEdit = zkRevenueEdit;
		this.zkRevenueConfirm = zkRevenueConfirm;
		this.zkRevenueDel = zkRevenueDel;
		this.zkRevenueSh = zkRevenueSh;
		this.zkCostEdit = zkCostEdit;
		this.zkCostSh = zkCostSh;
		this.zkToday = zkToday;
		this.shExchangeNew = shExchangeNew;
		this.shExchangeIn = shExchangeIn;
		this.shExchangeOut = shExchangeOut;
		this.shExchangeCharge = shExchangeCharge;
		this.shExchangeEdit = shExchangeEdit;
		this.shExchangeSh = shExchangeSh;
		this.shVist = shVist;
		this.shVistSh = shVistSh;
		this.shRepairReg = shRepairReg;
		this.shRepairDo = shRepairDo;
		this.shRepairCharge = shRepairCharge;
		this.shRepairSnd = shRepairSnd;
		this.shRepairSh = shRepairSh;
		this.shMsc = shMsc;
		this.xtBackup = xtBackup;
		this.xtExcel = xtExcel;
		this.xtBaseInfoEdit = xtBaseInfoEdit;
		this.xtBaseInfoView = xtBaseInfoView;
		this.xtEmailSet = xtEmailSet;
		this.xtEmailView = xtEmailView;
		this.xtCustom = xtCustom;
		this.FCostPrice = FCostPrice;
		this.FProfit = FProfit;
		this.FProvider = FProvider;
		this.FTask = FTask;
		this.FDepreciate = FDepreciate;
		this.bgSndNotice = bgSndNotice;
		this.bgTelReg = bgTelReg;
		this.bgTelSh = bgTelSh;
		this.bgCaseDo = bgCaseDo;
		this.bgCaseChk = bgCaseChk;
		this.bgSalary = bgSalary;
		this.bgNoticeView = bgNoticeView;
		this.bgNoticeEdit = bgNoticeEdit;
		this.tjSummary = tjSummary;
		this.tjSell = tjSell;
		this.tjPurchase = tjPurchase;
		this.tjStock = tjStock;
		this.tjService = tjService;
		this.tjCustomer = tjCustomer;
		this.tjSnd = tjSnd;
		this.tjMoney = tjMoney;
		this.tjEmployee = tjEmployee;
		this.tjAnalyse = tjAnalyse;
		this.hideSys = hideSys;
		this.hideBaseInfo = hideBaseInfo;
		this.hideGoods = hideGoods;
		this.hidePurchase = hidePurchase;
		this.hideSell = hideSell;
		this.hideStock = hideStock;
		this.hideCustomer = hideCustomer;
		this.hideBank = hideBank;
		this.hideService = hideService;
		this.hideMail = hideMail;
		this.hideOa = hideOa;
		this.hideSta = hideSta;
		this.hideAna = hideAna;
		this.hideCustom = hideCustom;
		this.hideTools = hideTools;
		this.hideHelp = hideHelp;
		this.hideToolsBar = hideToolsBar;
		this.zkMoneyTraceView = zkMoneyTraceView;
		this.cgPurchaseReturnDel = cgPurchaseReturnDel;
		this.cgPurchaseDel = cgPurchaseDel;
		this.xsTradeDel = xsTradeDel;
		this.xsBargainSh = xsBargainSh;
		this.kcStockSh = kcStockSh;
		this.xsCloseTrace = xsCloseTrace;
		this.bgCaseReg = bgCaseReg;
		this.bgCaseSh = bgCaseSh;
		this.zkCodcharge = zkCodcharge;
		this.zkCodedit = zkCodedit;
		this.zkCodsh = zkCodsh;
		this.cgPurchaseChk = cgPurchaseChk;
		this.cgPurchaseDelCharge = cgPurchaseDelCharge;
		this.cgPurchaseBack = cgPurchaseBack;
		this.cgPurchaseStop = cgPurchaseStop;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getWelcomeStyle() {
		return this.welcomeStyle;
	}

	public void setWelcomeStyle(String welcomeStyle) {
		this.welcomeStyle = welcomeStyle;
	}

	public Boolean getCgPurchaseReg() {
		return this.cgPurchaseReg;
	}

	public void setCgPurchaseReg(Boolean cgPurchaseReg) {
		this.cgPurchaseReg = cgPurchaseReg;
	}

	public Boolean getCgPurchaseCharge() {
		return this.cgPurchaseCharge;
	}

	public void setCgPurchaseCharge(Boolean cgPurchaseCharge) {
		this.cgPurchaseCharge = cgPurchaseCharge;
	}

	public Boolean getCgPurchaseStockIn() {
		return this.cgPurchaseStockIn;
	}

	public void setCgPurchaseStockIn(Boolean cgPurchaseStockIn) {
		this.cgPurchaseStockIn = cgPurchaseStockIn;
	}

	public Boolean getCgPurchaseReturn() {
		return this.cgPurchaseReturn;
	}

	public void setCgPurchaseReturn(Boolean cgPurchaseReturn) {
		this.cgPurchaseReturn = cgPurchaseReturn;
	}

	public Boolean getCgPurchaseSh() {
		return this.cgPurchaseSh;
	}

	public void setCgPurchaseSh(Boolean cgPurchaseSh) {
		this.cgPurchaseSh = cgPurchaseSh;
	}

	public Boolean getCgPurchaseReturnSh() {
		return this.cgPurchaseReturnSh;
	}

	public void setCgPurchaseReturnSh(Boolean cgPurchaseReturnSh) {
		this.cgPurchaseReturnSh = cgPurchaseReturnSh;
	}

	public Boolean getXsTradeReg() {
		return this.xsTradeReg;
	}

	public void setXsTradeReg(Boolean xsTradeReg) {
		this.xsTradeReg = xsTradeReg;
	}

	public Boolean getXsPos() {
		return this.xsPos;
	}

	public void setXsPos(Boolean xsPos) {
		this.xsPos = xsPos;
	}

	public Boolean getXsTradeCancel() {
		return this.xsTradeCancel;
	}

	public void setXsTradeCancel(Boolean xsTradeCancel) {
		this.xsTradeCancel = xsTradeCancel;
	}

	public Boolean getXsTradeEdit() {
		return this.xsTradeEdit;
	}

	public void setXsTradeEdit(Boolean xsTradeEdit) {
		this.xsTradeEdit = xsTradeEdit;
	}

	public Boolean getXsTradeConfirm() {
		return this.xsTradeConfirm;
	}

	public void setXsTradeConfirm(Boolean xsTradeConfirm) {
		this.xsTradeConfirm = xsTradeConfirm;
	}

	public Boolean getXsTradeCharge() {
		return this.xsTradeCharge;
	}

	public void setXsTradeCharge(Boolean xsTradeCharge) {
		this.xsTradeCharge = xsTradeCharge;
	}

	public Boolean getXsTradeChargeCancel() {
		return this.xsTradeChargeCancel;
	}

	public void setXsTradeChargeCancel(Boolean xsTradeChargeCancel) {
		this.xsTradeChargeCancel = xsTradeChargeCancel;
	}

	public Boolean getXsTradeStockOut() {
		return this.xsTradeStockOut;
	}

	public void setXsTradeStockOut(Boolean xsTradeStockOut) {
		this.xsTradeStockOut = xsTradeStockOut;
	}

	public Boolean getXsTradeStockOutCancel() {
		return this.xsTradeStockOutCancel;
	}

	public void setXsTradeStockOutCancel(Boolean xsTradeStockOutCancel) {
		this.xsTradeStockOutCancel = xsTradeStockOutCancel;
	}

	public Boolean getXsPrintSndBill() {
		return this.xsPrintSndBill;
	}

	public void setXsPrintSndBill(Boolean xsPrintSndBill) {
		this.xsPrintSndBill = xsPrintSndBill;
	}

	public Boolean getXsPrintExpress() {
		return this.xsPrintExpress;
	}

	public void setXsPrintExpress(Boolean xsPrintExpress) {
		this.xsPrintExpress = xsPrintExpress;
	}

	public Boolean getXsStockOutChk() {
		return this.xsStockOutChk;
	}

	public void setXsStockOutChk(Boolean xsStockOutChk) {
		this.xsStockOutChk = xsStockOutChk;
	}

	public Boolean getXsTradeClose() {
		return this.xsTradeClose;
	}

	public void setXsTradeClose(Boolean xsTradeClose) {
		this.xsTradeClose = xsTradeClose;
	}

	public Boolean getXsBargain() {
		return this.xsBargain;
	}

	public void setXsBargain(Boolean xsBargain) {
		this.xsBargain = xsBargain;
	}

	public Boolean getXsTradeSh() {
		return this.xsTradeSh;
	}

	public void setXsTradeSh(Boolean xsTradeSh) {
		this.xsTradeSh = xsTradeSh;
	}

	public Boolean getXsUnTradeCancel() {
		return this.xsUnTradeCancel;
	}

	public void setXsUnTradeCancel(Boolean xsUnTradeCancel) {
		this.xsUnTradeCancel = xsUnTradeCancel;
	}

	public Boolean getXsUnTradeClose() {
		return this.xsUnTradeClose;
	}

	public void setXsUnTradeClose(Boolean xsUnTradeClose) {
		this.xsUnTradeClose = xsUnTradeClose;
	}

	public Boolean getXsOutPutExcel() {
		return this.xsOutPutExcel;
	}

	public void setXsOutPutExcel(Boolean xsOutPutExcel) {
		this.xsOutPutExcel = xsOutPutExcel;
	}

	public Boolean getXsOtherPrint() {
		return this.xsOtherPrint;
	}

	public void setXsOtherPrint(Boolean xsOtherPrint) {
		this.xsOtherPrint = xsOtherPrint;
	}

	public Boolean getXsEditOperator() {
		return this.xsEditOperator;
	}

	public void setXsEditOperator(Boolean xsEditOperator) {
		this.xsEditOperator = xsEditOperator;
	}

	public Boolean getXsEditBaseInfo() {
		return this.xsEditBaseInfo;
	}

	public void setXsEditBaseInfo(Boolean xsEditBaseInfo) {
		this.xsEditBaseInfo = xsEditBaseInfo;
	}

	public Boolean getXsEditCustomerInfo() {
		return this.xsEditCustomerInfo;
	}

	public void setXsEditCustomerInfo(Boolean xsEditCustomerInfo) {
		this.xsEditCustomerInfo = xsEditCustomerInfo;
	}

	public Boolean getXsEditChargeInfo() {
		return this.xsEditChargeInfo;
	}

	public void setXsEditChargeInfo(Boolean xsEditChargeInfo) {
		this.xsEditChargeInfo = xsEditChargeInfo;
	}

	public Boolean getHpEditGoods() {
		return this.hpEditGoods;
	}

	public void setHpEditGoods(Boolean hpEditGoods) {
		this.hpEditGoods = hpEditGoods;
	}

	public Boolean getHpGoodsSh() {
		return this.hpGoodsSh;
	}

	public void setHpGoodsSh(Boolean hpGoodsSh) {
		this.hpGoodsSh = hpGoodsSh;
	}

	public Boolean getHpDelGoods() {
		return this.hpDelGoods;
	}

	public void setHpDelGoods(Boolean hpDelGoods) {
		this.hpDelGoods = hpDelGoods;
	}

	public Boolean getHpEditGoodsChk() {
		return this.hpEditGoodsChk;
	}

	public void setHpEditGoodsChk(Boolean hpEditGoodsChk) {
		this.hpEditGoodsChk = hpEditGoodsChk;
	}

	public Boolean getHpCreateGoodsPic() {
		return this.hpCreateGoodsPic;
	}

	public void setHpCreateGoodsPic(Boolean hpCreateGoodsPic) {
		this.hpCreateGoodsPic = hpCreateGoodsPic;
	}

	public Boolean getHpEditGoodsPic() {
		return this.hpEditGoodsPic;
	}

	public void setHpEditGoodsPic(Boolean hpEditGoodsPic) {
		this.hpEditGoodsPic = hpEditGoodsPic;
	}

	public Boolean getHpEditGoodsPicChk() {
		return this.hpEditGoodsPicChk;
	}

	public void setHpEditGoodsPicChk(Boolean hpEditGoodsPicChk) {
		this.hpEditGoodsPicChk = hpEditGoodsPicChk;
	}

	public Boolean getHpGoodsInfoExcel() {
		return this.hpGoodsInfoExcel;
	}

	public void setHpGoodsInfoExcel(Boolean hpGoodsInfoExcel) {
		this.hpGoodsInfoExcel = hpGoodsInfoExcel;
	}

	public Boolean getKcStockIn() {
		return this.kcStockIn;
	}

	public void setKcStockIn(Boolean kcStockIn) {
		this.kcStockIn = kcStockIn;
	}

	public Boolean getKcStockInChk() {
		return this.kcStockInChk;
	}

	public void setKcStockInChk(Boolean kcStockInChk) {
		this.kcStockInChk = kcStockInChk;
	}

	public Boolean getKcStockInSh() {
		return this.kcStockInSh;
	}

	public void setKcStockInSh(Boolean kcStockInSh) {
		this.kcStockInSh = kcStockInSh;
	}

	public Boolean getKcStockOut() {
		return this.kcStockOut;
	}

	public void setKcStockOut(Boolean kcStockOut) {
		this.kcStockOut = kcStockOut;
	}

	public Boolean getKcStockOutChk() {
		return this.kcStockOutChk;
	}

	public void setKcStockOutChk(Boolean kcStockOutChk) {
		this.kcStockOutChk = kcStockOutChk;
	}

	public Boolean getKcStockOutSh() {
		return this.kcStockOutSh;
	}

	public void setKcStockOutSh(Boolean kcStockOutSh) {
		this.kcStockOutSh = kcStockOutSh;
	}

	public Boolean getKcStockCheck() {
		return this.kcStockCheck;
	}

	public void setKcStockCheck(Boolean kcStockCheck) {
		this.kcStockCheck = kcStockCheck;
	}

	public Boolean getKcStockCheckSh() {
		return this.kcStockCheckSh;
	}

	public void setKcStockCheckSh(Boolean kcStockCheckSh) {
		this.kcStockCheckSh = kcStockCheckSh;
	}

	public Boolean getKcStockInDel() {
		return this.kcStockInDel;
	}

	public void setKcStockInDel(Boolean kcStockInDel) {
		this.kcStockInDel = kcStockInDel;
	}

	public Boolean getKcStockOutDel() {
		return this.kcStockOutDel;
	}

	public void setKcStockOutDel(Boolean kcStockOutDel) {
		this.kcStockOutDel = kcStockOutDel;
	}

	public Boolean getKhEditCustomerInfo() {
		return this.khEditCustomerInfo;
	}

	public void setKhEditCustomerInfo(Boolean khEditCustomerInfo) {
		this.khEditCustomerInfo = khEditCustomerInfo;
	}

	public Boolean getKhCustomerDel() {
		return this.khCustomerDel;
	}

	public void setKhCustomerDel(Boolean khCustomerDel) {
		this.khCustomerDel = khCustomerDel;
	}

	public Boolean getKhCustomerSh() {
		return this.khCustomerSh;
	}

	public void setKhCustomerSh(Boolean khCustomerSh) {
		this.khCustomerSh = khCustomerSh;
	}

	public Boolean getKhCustomerExcel() {
		return this.khCustomerExcel;
	}

	public void setKhCustomerExcel(Boolean khCustomerExcel) {
		this.khCustomerExcel = khCustomerExcel;
	}

	public Boolean getKhEditProviderInfo() {
		return this.khEditProviderInfo;
	}

	public void setKhEditProviderInfo(Boolean khEditProviderInfo) {
		this.khEditProviderInfo = khEditProviderInfo;
	}

	public Boolean getKhProviderDel() {
		return this.khProviderDel;
	}

	public void setKhProviderDel(Boolean khProviderDel) {
		this.khProviderDel = khProviderDel;
	}

	public Boolean getKhProviderSh() {
		return this.khProviderSh;
	}

	public void setKhProviderSh(Boolean khProviderSh) {
		this.khProviderSh = khProviderSh;
	}

	public Boolean getKhProviderExcel() {
		return this.khProviderExcel;
	}

	public void setKhProviderExcel(Boolean khProviderExcel) {
		this.khProviderExcel = khProviderExcel;
	}

	public Boolean getKhEditEnemyInfo() {
		return this.khEditEnemyInfo;
	}

	public void setKhEditEnemyInfo(Boolean khEditEnemyInfo) {
		this.khEditEnemyInfo = khEditEnemyInfo;
	}

	public Boolean getKhEnemyDel() {
		return this.khEnemyDel;
	}

	public void setKhEnemyDel(Boolean khEnemyDel) {
		this.khEnemyDel = khEnemyDel;
	}

	public Boolean getKhEnemySh() {
		return this.khEnemySh;
	}

	public void setKhEnemySh(Boolean khEnemySh) {
		this.khEnemySh = khEnemySh;
	}

	public Boolean getKhEnemyExcel() {
		return this.khEnemyExcel;
	}

	public void setKhEnemyExcel(Boolean khEnemyExcel) {
		this.khEnemyExcel = khEnemyExcel;
	}

	public Boolean getKhCustomerScore() {
		return this.khCustomerScore;
	}

	public void setKhCustomerScore(Boolean khCustomerScore) {
		this.khCustomerScore = khCustomerScore;
	}

	public Boolean getKhCustomerPredeposit() {
		return this.khCustomerPredeposit;
	}

	public void setKhCustomerPredeposit(Boolean khCustomerPredeposit) {
		this.khCustomerPredeposit = khCustomerPredeposit;
	}

	public Boolean getZkNewInOut() {
		return this.zkNewInOut;
	}

	public void setZkNewInOut(Boolean zkNewInOut) {
		this.zkNewInOut = zkNewInOut;
	}

	public Boolean getZkInOutChk() {
		return this.zkInOutChk;
	}

	public void setZkInOutChk(Boolean zkInOutChk) {
		this.zkInOutChk = zkInOutChk;
	}

	public Boolean getZkBank() {
		return this.zkBank;
	}

	public void setZkBank(Boolean zkBank) {
		this.zkBank = zkBank;
	}

	public Boolean getZkBankExcel() {
		return this.zkBankExcel;
	}

	public void setZkBankExcel(Boolean zkBankExcel) {
		this.zkBankExcel = zkBankExcel;
	}

	public Boolean getZkInOutEdit() {
		return this.zkInOutEdit;
	}

	public void setZkInOutEdit(Boolean zkInOutEdit) {
		this.zkInOutEdit = zkInOutEdit;
	}

	public Boolean getZkInOutDel() {
		return this.zkInOutDel;
	}

	public void setZkInOutDel(Boolean zkInOutDel) {
		this.zkInOutDel = zkInOutDel;
	}

	public Boolean getZkMoneyTraceEdit() {
		return this.zkMoneyTraceEdit;
	}

	public void setZkMoneyTraceEdit(Boolean zkMoneyTraceEdit) {
		this.zkMoneyTraceEdit = zkMoneyTraceEdit;
	}

	public Boolean getZkMoneyTraceDel() {
		return this.zkMoneyTraceDel;
	}

	public void setZkMoneyTraceDel(Boolean zkMoneyTraceDel) {
		this.zkMoneyTraceDel = zkMoneyTraceDel;
	}

	public Boolean getZkMoneyTraceCharge() {
		return this.zkMoneyTraceCharge;
	}

	public void setZkMoneyTraceCharge(Boolean zkMoneyTraceCharge) {
		this.zkMoneyTraceCharge = zkMoneyTraceCharge;
	}

	public Boolean getZkMoneyTraceCancel() {
		return this.zkMoneyTraceCancel;
	}

	public void setZkMoneyTraceCancel(Boolean zkMoneyTraceCancel) {
		this.zkMoneyTraceCancel = zkMoneyTraceCancel;
	}

	public Boolean getZkPostageCharge() {
		return this.zkPostageCharge;
	}

	public void setZkPostageCharge(Boolean zkPostageCharge) {
		this.zkPostageCharge = zkPostageCharge;
	}

	public Boolean getZkPostageSh() {
		return this.zkPostageSh;
	}

	public void setZkPostageSh(Boolean zkPostageSh) {
		this.zkPostageSh = zkPostageSh;
	}

	public Boolean getZkRevenueEdit() {
		return this.zkRevenueEdit;
	}

	public void setZkRevenueEdit(Boolean zkRevenueEdit) {
		this.zkRevenueEdit = zkRevenueEdit;
	}

	public Boolean getZkRevenueConfirm() {
		return this.zkRevenueConfirm;
	}

	public void setZkRevenueConfirm(Boolean zkRevenueConfirm) {
		this.zkRevenueConfirm = zkRevenueConfirm;
	}

	public Boolean getZkRevenueDel() {
		return this.zkRevenueDel;
	}

	public void setZkRevenueDel(Boolean zkRevenueDel) {
		this.zkRevenueDel = zkRevenueDel;
	}

	public Boolean getZkRevenueSh() {
		return this.zkRevenueSh;
	}

	public void setZkRevenueSh(Boolean zkRevenueSh) {
		this.zkRevenueSh = zkRevenueSh;
	}

	public Boolean getZkCostEdit() {
		return this.zkCostEdit;
	}

	public void setZkCostEdit(Boolean zkCostEdit) {
		this.zkCostEdit = zkCostEdit;
	}

	public Boolean getZkCostSh() {
		return this.zkCostSh;
	}

	public void setZkCostSh(Boolean zkCostSh) {
		this.zkCostSh = zkCostSh;
	}

	public Boolean getZkToday() {
		return this.zkToday;
	}

	public void setZkToday(Boolean zkToday) {
		this.zkToday = zkToday;
	}

	public Boolean getShExchangeNew() {
		return this.shExchangeNew;
	}

	public void setShExchangeNew(Boolean shExchangeNew) {
		this.shExchangeNew = shExchangeNew;
	}

	public Boolean getShExchangeIn() {
		return this.shExchangeIn;
	}

	public void setShExchangeIn(Boolean shExchangeIn) {
		this.shExchangeIn = shExchangeIn;
	}

	public Boolean getShExchangeOut() {
		return this.shExchangeOut;
	}

	public void setShExchangeOut(Boolean shExchangeOut) {
		this.shExchangeOut = shExchangeOut;
	}

	public Boolean getShExchangeCharge() {
		return this.shExchangeCharge;
	}

	public void setShExchangeCharge(Boolean shExchangeCharge) {
		this.shExchangeCharge = shExchangeCharge;
	}

	public Boolean getShExchangeEdit() {
		return this.shExchangeEdit;
	}

	public void setShExchangeEdit(Boolean shExchangeEdit) {
		this.shExchangeEdit = shExchangeEdit;
	}

	public Boolean getShExchangeSh() {
		return this.shExchangeSh;
	}

	public void setShExchangeSh(Boolean shExchangeSh) {
		this.shExchangeSh = shExchangeSh;
	}

	public Boolean getShVist() {
		return this.shVist;
	}

	public void setShVist(Boolean shVist) {
		this.shVist = shVist;
	}

	public Boolean getShVistSh() {
		return this.shVistSh;
	}

	public void setShVistSh(Boolean shVistSh) {
		this.shVistSh = shVistSh;
	}

	public Boolean getShRepairReg() {
		return this.shRepairReg;
	}

	public void setShRepairReg(Boolean shRepairReg) {
		this.shRepairReg = shRepairReg;
	}

	public Boolean getShRepairDo() {
		return this.shRepairDo;
	}

	public void setShRepairDo(Boolean shRepairDo) {
		this.shRepairDo = shRepairDo;
	}

	public Boolean getShRepairCharge() {
		return this.shRepairCharge;
	}

	public void setShRepairCharge(Boolean shRepairCharge) {
		this.shRepairCharge = shRepairCharge;
	}

	public Boolean getShRepairSnd() {
		return this.shRepairSnd;
	}

	public void setShRepairSnd(Boolean shRepairSnd) {
		this.shRepairSnd = shRepairSnd;
	}

	public Boolean getShRepairSh() {
		return this.shRepairSh;
	}

	public void setShRepairSh(Boolean shRepairSh) {
		this.shRepairSh = shRepairSh;
	}

	public Boolean getShMsc() {
		return this.shMsc;
	}

	public void setShMsc(Boolean shMsc) {
		this.shMsc = shMsc;
	}

	public Boolean getXtBackup() {
		return this.xtBackup;
	}

	public void setXtBackup(Boolean xtBackup) {
		this.xtBackup = xtBackup;
	}

	public Boolean getXtExcel() {
		return this.xtExcel;
	}

	public void setXtExcel(Boolean xtExcel) {
		this.xtExcel = xtExcel;
	}

	public Boolean getXtBaseInfoEdit() {
		return this.xtBaseInfoEdit;
	}

	public void setXtBaseInfoEdit(Boolean xtBaseInfoEdit) {
		this.xtBaseInfoEdit = xtBaseInfoEdit;
	}

	public Boolean getXtBaseInfoView() {
		return this.xtBaseInfoView;
	}

	public void setXtBaseInfoView(Boolean xtBaseInfoView) {
		this.xtBaseInfoView = xtBaseInfoView;
	}

	public Boolean getXtEmailSet() {
		return this.xtEmailSet;
	}

	public void setXtEmailSet(Boolean xtEmailSet) {
		this.xtEmailSet = xtEmailSet;
	}

	public Boolean getXtEmailView() {
		return this.xtEmailView;
	}

	public void setXtEmailView(Boolean xtEmailView) {
		this.xtEmailView = xtEmailView;
	}

	public Boolean getXtCustom() {
		return this.xtCustom;
	}

	public void setXtCustom(Boolean xtCustom) {
		this.xtCustom = xtCustom;
	}

	public Boolean getFCostPrice() {
		return this.FCostPrice;
	}

	public void setFCostPrice(Boolean FCostPrice) {
		this.FCostPrice = FCostPrice;
	}

	public Boolean getFProfit() {
		return this.FProfit;
	}

	public void setFProfit(Boolean FProfit) {
		this.FProfit = FProfit;
	}

	public Boolean getFProvider() {
		return this.FProvider;
	}

	public void setFProvider(Boolean FProvider) {
		this.FProvider = FProvider;
	}

	public Boolean getFTask() {
		return this.FTask;
	}

	public void setFTask(Boolean FTask) {
		this.FTask = FTask;
	}

	public Boolean getFDepreciate() {
		return this.FDepreciate;
	}

	public void setFDepreciate(Boolean FDepreciate) {
		this.FDepreciate = FDepreciate;
	}

	public Boolean getBgSndNotice() {
		return this.bgSndNotice;
	}

	public void setBgSndNotice(Boolean bgSndNotice) {
		this.bgSndNotice = bgSndNotice;
	}

	public Boolean getBgTelReg() {
		return this.bgTelReg;
	}

	public void setBgTelReg(Boolean bgTelReg) {
		this.bgTelReg = bgTelReg;
	}

	public Boolean getBgTelSh() {
		return this.bgTelSh;
	}

	public void setBgTelSh(Boolean bgTelSh) {
		this.bgTelSh = bgTelSh;
	}

	public Boolean getBgCaseDo() {
		return this.bgCaseDo;
	}

	public void setBgCaseDo(Boolean bgCaseDo) {
		this.bgCaseDo = bgCaseDo;
	}

	public Boolean getBgCaseChk() {
		return this.bgCaseChk;
	}

	public void setBgCaseChk(Boolean bgCaseChk) {
		this.bgCaseChk = bgCaseChk;
	}

	public Boolean getBgSalary() {
		return this.bgSalary;
	}

	public void setBgSalary(Boolean bgSalary) {
		this.bgSalary = bgSalary;
	}

	public Boolean getBgNoticeView() {
		return this.bgNoticeView;
	}

	public void setBgNoticeView(Boolean bgNoticeView) {
		this.bgNoticeView = bgNoticeView;
	}

	public Boolean getBgNoticeEdit() {
		return this.bgNoticeEdit;
	}

	public void setBgNoticeEdit(Boolean bgNoticeEdit) {
		this.bgNoticeEdit = bgNoticeEdit;
	}

	public Boolean getTjSummary() {
		return this.tjSummary;
	}

	public void setTjSummary(Boolean tjSummary) {
		this.tjSummary = tjSummary;
	}

	public Boolean getTjSell() {
		return this.tjSell;
	}

	public void setTjSell(Boolean tjSell) {
		this.tjSell = tjSell;
	}

	public Boolean getTjPurchase() {
		return this.tjPurchase;
	}

	public void setTjPurchase(Boolean tjPurchase) {
		this.tjPurchase = tjPurchase;
	}

	public Boolean getTjStock() {
		return this.tjStock;
	}

	public void setTjStock(Boolean tjStock) {
		this.tjStock = tjStock;
	}

	public Boolean getTjService() {
		return this.tjService;
	}

	public void setTjService(Boolean tjService) {
		this.tjService = tjService;
	}

	public Boolean getTjCustomer() {
		return this.tjCustomer;
	}

	public void setTjCustomer(Boolean tjCustomer) {
		this.tjCustomer = tjCustomer;
	}

	public Boolean getTjSnd() {
		return this.tjSnd;
	}

	public void setTjSnd(Boolean tjSnd) {
		this.tjSnd = tjSnd;
	}

	public Boolean getTjMoney() {
		return this.tjMoney;
	}

	public void setTjMoney(Boolean tjMoney) {
		this.tjMoney = tjMoney;
	}

	public Boolean getTjEmployee() {
		return this.tjEmployee;
	}

	public void setTjEmployee(Boolean tjEmployee) {
		this.tjEmployee = tjEmployee;
	}

	public Boolean getTjAnalyse() {
		return this.tjAnalyse;
	}

	public void setTjAnalyse(Boolean tjAnalyse) {
		this.tjAnalyse = tjAnalyse;
	}

	public Boolean getHideSys() {
		return this.hideSys;
	}

	public void setHideSys(Boolean hideSys) {
		this.hideSys = hideSys;
	}

	public Boolean getHideBaseInfo() {
		return this.hideBaseInfo;
	}

	public void setHideBaseInfo(Boolean hideBaseInfo) {
		this.hideBaseInfo = hideBaseInfo;
	}

	public Boolean getHideGoods() {
		return this.hideGoods;
	}

	public void setHideGoods(Boolean hideGoods) {
		this.hideGoods = hideGoods;
	}

	public Boolean getHidePurchase() {
		return this.hidePurchase;
	}

	public void setHidePurchase(Boolean hidePurchase) {
		this.hidePurchase = hidePurchase;
	}

	public Boolean getHideSell() {
		return this.hideSell;
	}

	public void setHideSell(Boolean hideSell) {
		this.hideSell = hideSell;
	}

	public Boolean getHideStock() {
		return this.hideStock;
	}

	public void setHideStock(Boolean hideStock) {
		this.hideStock = hideStock;
	}

	public Boolean getHideCustomer() {
		return this.hideCustomer;
	}

	public void setHideCustomer(Boolean hideCustomer) {
		this.hideCustomer = hideCustomer;
	}

	public Boolean getHideBank() {
		return this.hideBank;
	}

	public void setHideBank(Boolean hideBank) {
		this.hideBank = hideBank;
	}

	public Boolean getHideService() {
		return this.hideService;
	}

	public void setHideService(Boolean hideService) {
		this.hideService = hideService;
	}

	public Boolean getHideMail() {
		return this.hideMail;
	}

	public void setHideMail(Boolean hideMail) {
		this.hideMail = hideMail;
	}

	public Boolean getHideOa() {
		return this.hideOa;
	}

	public void setHideOa(Boolean hideOa) {
		this.hideOa = hideOa;
	}

	public Boolean getHideSta() {
		return this.hideSta;
	}

	public void setHideSta(Boolean hideSta) {
		this.hideSta = hideSta;
	}

	public Boolean getHideAna() {
		return this.hideAna;
	}

	public void setHideAna(Boolean hideAna) {
		this.hideAna = hideAna;
	}

	public Boolean getHideCustom() {
		return this.hideCustom;
	}

	public void setHideCustom(Boolean hideCustom) {
		this.hideCustom = hideCustom;
	}

	public Boolean getHideTools() {
		return this.hideTools;
	}

	public void setHideTools(Boolean hideTools) {
		this.hideTools = hideTools;
	}

	public Boolean getHideHelp() {
		return this.hideHelp;
	}

	public void setHideHelp(Boolean hideHelp) {
		this.hideHelp = hideHelp;
	}

	public Boolean getHideToolsBar() {
		return this.hideToolsBar;
	}

	public void setHideToolsBar(Boolean hideToolsBar) {
		this.hideToolsBar = hideToolsBar;
	}

	public Boolean getZkMoneyTraceView() {
		return this.zkMoneyTraceView;
	}

	public void setZkMoneyTraceView(Boolean zkMoneyTraceView) {
		this.zkMoneyTraceView = zkMoneyTraceView;
	}

	public Boolean getCgPurchaseReturnDel() {
		return this.cgPurchaseReturnDel;
	}

	public void setCgPurchaseReturnDel(Boolean cgPurchaseReturnDel) {
		this.cgPurchaseReturnDel = cgPurchaseReturnDel;
	}

	public Boolean getCgPurchaseDel() {
		return this.cgPurchaseDel;
	}

	public void setCgPurchaseDel(Boolean cgPurchaseDel) {
		this.cgPurchaseDel = cgPurchaseDel;
	}

	public Boolean getXsTradeDel() {
		return this.xsTradeDel;
	}

	public void setXsTradeDel(Boolean xsTradeDel) {
		this.xsTradeDel = xsTradeDel;
	}

	public Boolean getXsBargainSh() {
		return this.xsBargainSh;
	}

	public void setXsBargainSh(Boolean xsBargainSh) {
		this.xsBargainSh = xsBargainSh;
	}

	public Boolean getKcStockSh() {
		return this.kcStockSh;
	}

	public void setKcStockSh(Boolean kcStockSh) {
		this.kcStockSh = kcStockSh;
	}

	public Boolean getXsCloseTrace() {
		return this.xsCloseTrace;
	}

	public void setXsCloseTrace(Boolean xsCloseTrace) {
		this.xsCloseTrace = xsCloseTrace;
	}

	public Boolean getBgCaseReg() {
		return this.bgCaseReg;
	}

	public void setBgCaseReg(Boolean bgCaseReg) {
		this.bgCaseReg = bgCaseReg;
	}

	public Boolean getBgCaseSh() {
		return this.bgCaseSh;
	}

	public void setBgCaseSh(Boolean bgCaseSh) {
		this.bgCaseSh = bgCaseSh;
	}

	public Boolean getZkCodcharge() {
		return this.zkCodcharge;
	}

	public void setZkCodcharge(Boolean zkCodcharge) {
		this.zkCodcharge = zkCodcharge;
	}

	public Boolean getZkCodedit() {
		return this.zkCodedit;
	}

	public void setZkCodedit(Boolean zkCodedit) {
		this.zkCodedit = zkCodedit;
	}

	public Boolean getZkCodsh() {
		return this.zkCodsh;
	}

	public void setZkCodsh(Boolean zkCodsh) {
		this.zkCodsh = zkCodsh;
	}

	public Boolean getCgPurchaseChk() {
		return this.cgPurchaseChk;
	}

	public void setCgPurchaseChk(Boolean cgPurchaseChk) {
		this.cgPurchaseChk = cgPurchaseChk;
	}

	public Boolean getCgPurchaseDelCharge() {
		return this.cgPurchaseDelCharge;
	}

	public void setCgPurchaseDelCharge(Boolean cgPurchaseDelCharge) {
		this.cgPurchaseDelCharge = cgPurchaseDelCharge;
	}

	public Boolean getCgPurchaseBack() {
		return this.cgPurchaseBack;
	}

	public void setCgPurchaseBack(Boolean cgPurchaseBack) {
		this.cgPurchaseBack = cgPurchaseBack;
	}

	public Boolean getCgPurchaseStop() {
		return this.cgPurchaseStop;
	}

	public void setCgPurchaseStop(Boolean cgPurchaseStop) {
		this.cgPurchaseStop = cgPurchaseStop;
	}

}
