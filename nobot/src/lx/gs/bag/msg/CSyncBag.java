
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSyncBag__ extends xio.Protocol { }

/** 同步背包，包括容量和物品
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSyncBag extends __CSyncBag__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570242;

	public int getType() {
		return 6570242;
	}

	public int bagtype;

	public CSyncBag() {
	}

	public CSyncBag(int _bagtype_) {
		this.bagtype = _bagtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSyncBag) {
			CSyncBag _o_ = (CSyncBag)_o1_;
			if (bagtype != _o_.bagtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSyncBag _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

