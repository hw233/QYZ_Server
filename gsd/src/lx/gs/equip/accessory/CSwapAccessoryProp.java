
package lx.gs.equip.accessory;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bag.msg.SItemBind;
import lx.gs.equip.FEquip;
import common.ErrorCode;
import xbean.AccessoryProp;
import xbean.Equip;
import xdb.Transaction;

import java.util.*;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSwapAccessoryProp__ extends xio.Protocol { }

/** 饰品转移
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSwapAccessoryProp extends __CSwapAccessoryProp__ {
	@Override
	protected void process() {
		new AProcedure<CSwapAccessoryProp>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				Equip accesory1 = FEquip.getEquip(roleid, bagtype1, pos1);
				Equip accesory2 = FEquip.getEquip(roleid, bagtype2, pos2);
				if(!FEquip.isAccessory(accesory1) || !FEquip.isAccessory(accesory2)){
					return error(ErrorCode.PARAM_ERROR);
				}

				if(FEquip.getEquipModel(accesory1).type != FEquip.getEquipModel(accesory2).type){
					return error(ErrorCode.ACC_TYPE_NOT_SAME);
				}

				List<AccessoryProp> prop1 = accesory1.getAccessory().getExtraprop();
				List<AccessoryProp> prop2 = accesory2.getAccessory().getExtraprop();
				List<AccessoryProp> temp1 = new ArrayList<>(prop1);
				List<AccessoryProp> temp2 = new ArrayList<>(prop2);
				prop1.clear();
				prop2.clear();
				prop1.addAll(temp2);
				prop2.addAll(temp1);

				if(accesory1.getIsbind() || accesory2.getIsbind()){
					if(!accesory1.getIsbind()){
						accesory1.setIsbind(true);
						Transaction.tsendWhileCommit(roleid, new SItemBind(bagtype1, pos1));
					}
					if(!accesory2.getIsbind()){
						accesory2.setIsbind(true);
						Transaction.tsendWhileCommit(roleid, new SItemBind(bagtype2, pos2));
					}
				}

				SSwapAccessoryProp result = new SSwapAccessoryProp();
				result.bagtype1 = bagtype1;
				result.bagtype2 = bagtype2;
				result.pos1 = pos1;
				result.pos2 = pos2;
				return response(result);
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583800;

	public int getType() {
		return 6583800;
	}

	public int bagtype1; // 饰品所在包裹类型
	public int pos1; // 饰品所在位置
	public int bagtype2; // 饰品所在包裹类型
	public int pos2; // 饰品所在位置

	public CSwapAccessoryProp() {
	}

	public CSwapAccessoryProp(int _bagtype1_, int _pos1_, int _bagtype2_, int _pos2_) {
		this.bagtype1 = _bagtype1_;
		this.pos1 = _pos1_;
		this.bagtype2 = _bagtype2_;
		this.pos2 = _pos2_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype1);
		_os_.marshal(pos1);
		_os_.marshal(bagtype2);
		_os_.marshal(pos2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype1 = _os_.unmarshal_int();
		pos1 = _os_.unmarshal_int();
		bagtype2 = _os_.unmarshal_int();
		pos2 = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSwapAccessoryProp) {
			CSwapAccessoryProp _o_ = (CSwapAccessoryProp)_o1_;
			if (bagtype1 != _o_.bagtype1) return false;
			if (pos1 != _o_.pos1) return false;
			if (bagtype2 != _o_.bagtype2) return false;
			if (pos2 != _o_.pos2) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype1;
		_h_ += pos1;
		_h_ += bagtype2;
		_h_ += pos2;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype1).append(",");
		_sb_.append(pos1).append(",");
		_sb_.append(bagtype2).append(",");
		_sb_.append(pos2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSwapAccessoryProp _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype1 - _o_.bagtype1;
		if (0 != _c_) return _c_;
		_c_ = pos1 - _o_.pos1;
		if (0 != _c_) return _c_;
		_c_ = bagtype2 - _o_.bagtype2;
		if (0 != _c_) return _c_;
		_c_ = pos2 - _o_.pos2;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

