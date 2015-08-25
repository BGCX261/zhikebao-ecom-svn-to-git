package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 商品类别
 */
public class GoodsClass implements java.io.Serializable {

	private int recId;
	private Integer level;
	private String name;
	private String code;
	private String father;
	private String reserved1;
	private String reserved2;
	private Integer reserved3;
	private String codeName;
	private Integer pos;
	private Boolean defineNo;
	private String definePre;
	private Integer definePos;
	private Integer defineLen;
	private Integer download;
	private Integer orderPos;
	private Boolean bchild;

	public GoodsClass() {
	}

	public GoodsClass(int recId) {
		this.recId = recId;
	}

	public GoodsClass(int recId, Integer level, String name, String code,
			String father, String reserved1, String reserved2,
			Integer reserved3, String codeName, Integer pos, Boolean defineNo,
			String definePre, Integer definePos, Integer defineLen,
			Integer download, Integer orderPos, Boolean bchild) {
		this.recId = recId;
		this.level = level;
		this.name = name;
		this.code = code;
		this.father = father;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.codeName = codeName;
		this.pos = pos;
		this.defineNo = defineNo;
		this.definePre = definePre;
		this.definePos = definePos;
		this.defineLen = defineLen;
		this.download = download;
		this.orderPos = orderPos;
		this.bchild = bchild;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public Integer getReserved3() {
		return this.reserved3;
	}

	public void setReserved3(Integer reserved3) {
		this.reserved3 = reserved3;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Integer getPos() {
		return this.pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Boolean getDefineNo() {
		return this.defineNo;
	}

	public void setDefineNo(Boolean defineNo) {
		this.defineNo = defineNo;
	}

	public String getDefinePre() {
		return this.definePre;
	}

	public void setDefinePre(String definePre) {
		this.definePre = definePre;
	}

	public Integer getDefinePos() {
		return this.definePos;
	}

	public void setDefinePos(Integer definePos) {
		this.definePos = definePos;
	}

	public Integer getDefineLen() {
		return this.defineLen;
	}

	public void setDefineLen(Integer defineLen) {
		this.defineLen = defineLen;
	}

	public Integer getDownload() {
		return this.download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getOrderPos() {
		return this.orderPos;
	}

	public void setOrderPos(Integer orderPos) {
		this.orderPos = orderPos;
	}

	public Boolean getBchild() {
		return this.bchild;
	}

	public void setBchild(Boolean bchild) {
		this.bchild = bchild;
	}

}
