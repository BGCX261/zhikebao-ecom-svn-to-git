package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础信息
 */
public class BaseInfo implements java.io.Serializable {

	private int recId;
	private String flag;
	private Boolean bhideZero;
	private Integer warningDay;
	private Integer seeBackDay;
	private Boolean negativeStock;
	private Boolean bseeBack;
	private String bakPath;
	private BigDecimal bscoreValue;
	private String statDate;
	private Boolean bscore;
	private Boolean upgrade;
	private Boolean showTip;
	private String defaultExpress;
	private Boolean bnotify;
	private String packageInclude;
	private Boolean bmultiSpec;
	private Boolean bupload;
	private String uploadUrl;
	private String uploadChk;
	private Boolean bmsc;
	private String urlcheck;
	private String msccode;
	private String mscurl;
	private String msctype;
	private Boolean bincludePostage;
	private Date initDate;
	private Boolean bdefaultPicTools;
	private String ver;
	private String smsUserId;
	private String smsPwd;
	private Boolean bstockCk;
	private Boolean brefuseBlackList;
	private String orderFlow;
	private Boolean bwarningProfitAftSnd;
	private Boolean bwarningZeroCost;
	private Boolean bstockInck;
	private Boolean skipPreView;
	private Boolean bminResource;
	private Boolean bmailNotity;
	private Boolean beditGoodsChk;
	private Boolean brefuseCreateGoods;
	private Integer npageSize;
	private Boolean bpackage;
	private Integer scoreStyle;
	private Boolean createCustomerNo;
	private String goodsNotagHead;
	private String goodsNotagTail;
	private Boolean bsearchGoodsNo;
	private Boolean bfx;
	private String fxCode;
	private String fxId;
	private Boolean bremarkControl;
	private String picServer;
	private Boolean bmultiSpec2;
	private Boolean breleaseFit;
	private Boolean btradeShowStock;
	private Boolean bsndClose;
	private Boolean brefuseCreateOrder;
	private Boolean bskipZeroPrice;
	private Boolean bpurchaseChk;

	public BaseInfo() {
	}

	public BaseInfo(int recId) {
		this.recId = recId;
	}

	public BaseInfo(int recId, String flag, Boolean bhideZero,
			Integer warningDay, Integer seeBackDay, Boolean negativeStock,
			Boolean bseeBack, String bakPath, BigDecimal bscoreValue,
			String statDate, Boolean bscore, Boolean upgrade, Boolean showTip,
			String defaultExpress, Boolean bnotify, String packageInclude,
			Boolean bmultiSpec, Boolean bupload, String uploadUrl,
			String uploadChk, Boolean bmsc, String urlcheck, String msccode,
			String mscurl, String msctype, Boolean bincludePostage,
			Date initDate, Boolean bdefaultPicTools, String ver,
			String smsUserId, String smsPwd, Boolean bstockCk,
			Boolean brefuseBlackList, String orderFlow,
			Boolean bwarningProfitAftSnd, Boolean bwarningZeroCost,
			Boolean bstockInck, Boolean skipPreView, Boolean bminResource,
			Boolean bmailNotity, Boolean beditGoodsChk,
			Boolean brefuseCreateGoods, Integer npageSize, Boolean bpackage,
			Integer scoreStyle, Boolean createCustomerNo,
			String goodsNotagHead, String goodsNotagTail,
			Boolean bsearchGoodsNo, Boolean bfx, String fxCode, String fxId,
			Boolean bremarkControl, String picServer, Boolean bmultiSpec2,
			Boolean breleaseFit, Boolean btradeShowStock, Boolean bsndClose,
			Boolean brefuseCreateOrder, Boolean bskipZeroPrice,
			Boolean bpurchaseChk) {
		this.recId = recId;
		this.flag = flag;
		this.bhideZero = bhideZero;
		this.warningDay = warningDay;
		this.seeBackDay = seeBackDay;
		this.negativeStock = negativeStock;
		this.bseeBack = bseeBack;
		this.bakPath = bakPath;
		this.bscoreValue = bscoreValue;
		this.statDate = statDate;
		this.bscore = bscore;
		this.upgrade = upgrade;
		this.showTip = showTip;
		this.defaultExpress = defaultExpress;
		this.bnotify = bnotify;
		this.packageInclude = packageInclude;
		this.bmultiSpec = bmultiSpec;
		this.bupload = bupload;
		this.uploadUrl = uploadUrl;
		this.uploadChk = uploadChk;
		this.bmsc = bmsc;
		this.urlcheck = urlcheck;
		this.msccode = msccode;
		this.mscurl = mscurl;
		this.msctype = msctype;
		this.bincludePostage = bincludePostage;
		this.initDate = initDate;
		this.bdefaultPicTools = bdefaultPicTools;
		this.ver = ver;
		this.smsUserId = smsUserId;
		this.smsPwd = smsPwd;
		this.bstockCk = bstockCk;
		this.brefuseBlackList = brefuseBlackList;
		this.orderFlow = orderFlow;
		this.bwarningProfitAftSnd = bwarningProfitAftSnd;
		this.bwarningZeroCost = bwarningZeroCost;
		this.bstockInck = bstockInck;
		this.skipPreView = skipPreView;
		this.bminResource = bminResource;
		this.bmailNotity = bmailNotity;
		this.beditGoodsChk = beditGoodsChk;
		this.brefuseCreateGoods = brefuseCreateGoods;
		this.npageSize = npageSize;
		this.bpackage = bpackage;
		this.scoreStyle = scoreStyle;
		this.createCustomerNo = createCustomerNo;
		this.goodsNotagHead = goodsNotagHead;
		this.goodsNotagTail = goodsNotagTail;
		this.bsearchGoodsNo = bsearchGoodsNo;
		this.bfx = bfx;
		this.fxCode = fxCode;
		this.fxId = fxId;
		this.bremarkControl = bremarkControl;
		this.picServer = picServer;
		this.bmultiSpec2 = bmultiSpec2;
		this.breleaseFit = breleaseFit;
		this.btradeShowStock = btradeShowStock;
		this.bsndClose = bsndClose;
		this.brefuseCreateOrder = brefuseCreateOrder;
		this.bskipZeroPrice = bskipZeroPrice;
		this.bpurchaseChk = bpurchaseChk;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Boolean getBhideZero() {
		return this.bhideZero;
	}

	public void setBhideZero(Boolean bhideZero) {
		this.bhideZero = bhideZero;
	}

	public Integer getWarningDay() {
		return this.warningDay;
	}

	public void setWarningDay(Integer warningDay) {
		this.warningDay = warningDay;
	}

	public Integer getSeeBackDay() {
		return this.seeBackDay;
	}

	public void setSeeBackDay(Integer seeBackDay) {
		this.seeBackDay = seeBackDay;
	}

	public Boolean getNegativeStock() {
		return this.negativeStock;
	}

	public void setNegativeStock(Boolean negativeStock) {
		this.negativeStock = negativeStock;
	}

	public Boolean getBseeBack() {
		return this.bseeBack;
	}

	public void setBseeBack(Boolean bseeBack) {
		this.bseeBack = bseeBack;
	}

	public String getBakPath() {
		return this.bakPath;
	}

	public void setBakPath(String bakPath) {
		this.bakPath = bakPath;
	}

	public BigDecimal getBscoreValue() {
		return this.bscoreValue;
	}

	public void setBscoreValue(BigDecimal bscoreValue) {
		this.bscoreValue = bscoreValue;
	}

	public String getStatDate() {
		return this.statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public Boolean getBscore() {
		return this.bscore;
	}

	public void setBscore(Boolean bscore) {
		this.bscore = bscore;
	}

	public Boolean getUpgrade() {
		return this.upgrade;
	}

	public void setUpgrade(Boolean upgrade) {
		this.upgrade = upgrade;
	}

	public Boolean getShowTip() {
		return this.showTip;
	}

	public void setShowTip(Boolean showTip) {
		this.showTip = showTip;
	}

	public String getDefaultExpress() {
		return this.defaultExpress;
	}

	public void setDefaultExpress(String defaultExpress) {
		this.defaultExpress = defaultExpress;
	}

	public Boolean getBnotify() {
		return this.bnotify;
	}

	public void setBnotify(Boolean bnotify) {
		this.bnotify = bnotify;
	}

	public String getPackageInclude() {
		return this.packageInclude;
	}

	public void setPackageInclude(String packageInclude) {
		this.packageInclude = packageInclude;
	}

	public Boolean getBmultiSpec() {
		return this.bmultiSpec;
	}

	public void setBmultiSpec(Boolean bmultiSpec) {
		this.bmultiSpec = bmultiSpec;
	}

	public Boolean getBupload() {
		return this.bupload;
	}

	public void setBupload(Boolean bupload) {
		this.bupload = bupload;
	}

	public String getUploadUrl() {
		return this.uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getUploadChk() {
		return this.uploadChk;
	}

	public void setUploadChk(String uploadChk) {
		this.uploadChk = uploadChk;
	}

	public Boolean getBmsc() {
		return this.bmsc;
	}

	public void setBmsc(Boolean bmsc) {
		this.bmsc = bmsc;
	}

	public String getUrlcheck() {
		return this.urlcheck;
	}

	public void setUrlcheck(String urlcheck) {
		this.urlcheck = urlcheck;
	}

	public String getMsccode() {
		return this.msccode;
	}

	public void setMsccode(String msccode) {
		this.msccode = msccode;
	}

	public String getMscurl() {
		return this.mscurl;
	}

	public void setMscurl(String mscurl) {
		this.mscurl = mscurl;
	}

	public String getMsctype() {
		return this.msctype;
	}

	public void setMsctype(String msctype) {
		this.msctype = msctype;
	}

	public Boolean getBincludePostage() {
		return this.bincludePostage;
	}

	public void setBincludePostage(Boolean bincludePostage) {
		this.bincludePostage = bincludePostage;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Boolean getBdefaultPicTools() {
		return this.bdefaultPicTools;
	}

	public void setBdefaultPicTools(Boolean bdefaultPicTools) {
		this.bdefaultPicTools = bdefaultPicTools;
	}

	public String getVer() {
		return this.ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getSmsUserId() {
		return this.smsUserId;
	}

	public void setSmsUserId(String smsUserId) {
		this.smsUserId = smsUserId;
	}

	public String getSmsPwd() {
		return this.smsPwd;
	}

	public void setSmsPwd(String smsPwd) {
		this.smsPwd = smsPwd;
	}

	public Boolean getBstockCk() {
		return this.bstockCk;
	}

	public void setBstockCk(Boolean bstockCk) {
		this.bstockCk = bstockCk;
	}

	public Boolean getBrefuseBlackList() {
		return this.brefuseBlackList;
	}

	public void setBrefuseBlackList(Boolean brefuseBlackList) {
		this.brefuseBlackList = brefuseBlackList;
	}

	public String getOrderFlow() {
		return this.orderFlow;
	}

	public void setOrderFlow(String orderFlow) {
		this.orderFlow = orderFlow;
	}

	public Boolean getBwarningProfitAftSnd() {
		return this.bwarningProfitAftSnd;
	}

	public void setBwarningProfitAftSnd(Boolean bwarningProfitAftSnd) {
		this.bwarningProfitAftSnd = bwarningProfitAftSnd;
	}

	public Boolean getBwarningZeroCost() {
		return this.bwarningZeroCost;
	}

	public void setBwarningZeroCost(Boolean bwarningZeroCost) {
		this.bwarningZeroCost = bwarningZeroCost;
	}

	public Boolean getBstockInck() {
		return this.bstockInck;
	}

	public void setBstockInck(Boolean bstockInck) {
		this.bstockInck = bstockInck;
	}

	public Boolean getSkipPreView() {
		return this.skipPreView;
	}

	public void setSkipPreView(Boolean skipPreView) {
		this.skipPreView = skipPreView;
	}

	public Boolean getBminResource() {
		return this.bminResource;
	}

	public void setBminResource(Boolean bminResource) {
		this.bminResource = bminResource;
	}

	public Boolean getBmailNotity() {
		return this.bmailNotity;
	}

	public void setBmailNotity(Boolean bmailNotity) {
		this.bmailNotity = bmailNotity;
	}

	public Boolean getBeditGoodsChk() {
		return this.beditGoodsChk;
	}

	public void setBeditGoodsChk(Boolean beditGoodsChk) {
		this.beditGoodsChk = beditGoodsChk;
	}

	public Boolean getBrefuseCreateGoods() {
		return this.brefuseCreateGoods;
	}

	public void setBrefuseCreateGoods(Boolean brefuseCreateGoods) {
		this.brefuseCreateGoods = brefuseCreateGoods;
	}

	public Integer getNpageSize() {
		return this.npageSize;
	}

	public void setNpageSize(Integer npageSize) {
		this.npageSize = npageSize;
	}

	public Boolean getBpackage() {
		return this.bpackage;
	}

	public void setBpackage(Boolean bpackage) {
		this.bpackage = bpackage;
	}

	public Integer getScoreStyle() {
		return this.scoreStyle;
	}

	public void setScoreStyle(Integer scoreStyle) {
		this.scoreStyle = scoreStyle;
	}

	public Boolean getCreateCustomerNo() {
		return this.createCustomerNo;
	}

	public void setCreateCustomerNo(Boolean createCustomerNo) {
		this.createCustomerNo = createCustomerNo;
	}

	public String getGoodsNotagHead() {
		return this.goodsNotagHead;
	}

	public void setGoodsNotagHead(String goodsNotagHead) {
		this.goodsNotagHead = goodsNotagHead;
	}

	public String getGoodsNotagTail() {
		return this.goodsNotagTail;
	}

	public void setGoodsNotagTail(String goodsNotagTail) {
		this.goodsNotagTail = goodsNotagTail;
	}

	public Boolean getBsearchGoodsNo() {
		return this.bsearchGoodsNo;
	}

	public void setBsearchGoodsNo(Boolean bsearchGoodsNo) {
		this.bsearchGoodsNo = bsearchGoodsNo;
	}

	public Boolean getBfx() {
		return this.bfx;
	}

	public void setBfx(Boolean bfx) {
		this.bfx = bfx;
	}

	public String getFxCode() {
		return this.fxCode;
	}

	public void setFxCode(String fxCode) {
		this.fxCode = fxCode;
	}

	public String getFxId() {
		return this.fxId;
	}

	public void setFxId(String fxId) {
		this.fxId = fxId;
	}

	public Boolean getBremarkControl() {
		return this.bremarkControl;
	}

	public void setBremarkControl(Boolean bremarkControl) {
		this.bremarkControl = bremarkControl;
	}

	public String getPicServer() {
		return this.picServer;
	}

	public void setPicServer(String picServer) {
		this.picServer = picServer;
	}

	public Boolean getBmultiSpec2() {
		return this.bmultiSpec2;
	}

	public void setBmultiSpec2(Boolean bmultiSpec2) {
		this.bmultiSpec2 = bmultiSpec2;
	}

	public Boolean getBreleaseFit() {
		return this.breleaseFit;
	}

	public void setBreleaseFit(Boolean breleaseFit) {
		this.breleaseFit = breleaseFit;
	}

	public Boolean getBtradeShowStock() {
		return this.btradeShowStock;
	}

	public void setBtradeShowStock(Boolean btradeShowStock) {
		this.btradeShowStock = btradeShowStock;
	}

	public Boolean getBsndClose() {
		return this.bsndClose;
	}

	public void setBsndClose(Boolean bsndClose) {
		this.bsndClose = bsndClose;
	}

	public Boolean getBrefuseCreateOrder() {
		return this.brefuseCreateOrder;
	}

	public void setBrefuseCreateOrder(Boolean brefuseCreateOrder) {
		this.brefuseCreateOrder = brefuseCreateOrder;
	}

	public Boolean getBskipZeroPrice() {
		return this.bskipZeroPrice;
	}

	public void setBskipZeroPrice(Boolean bskipZeroPrice) {
		this.bskipZeroPrice = bskipZeroPrice;
	}

	public Boolean getBpurchaseChk() {
		return this.bpurchaseChk;
	}

	public void setBpurchaseChk(Boolean bpurchaseChk) {
		this.bpurchaseChk = bpurchaseChk;
	}

}
