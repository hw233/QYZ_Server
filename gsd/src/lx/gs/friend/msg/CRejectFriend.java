
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SRejectFriend;
import lx.gs.friend.msg.SRejectFriendNotify;
import xdb.Lockeys;
import xdb.Transaction;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRejectFriend__ extends xio.Protocol { }

/** 拒绝好友申请，全部拒绝也调用此协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRejectFriend extends __CRejectFriend__ {
	@Override
	protected void process() {
		new AProcedure<CRejectFriend>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SRejectFriend result = new SRejectFriend();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(roleid);
                SRejectFriendNotify notify = new SRejectFriendNotify(FFriend.makeProtocolRoleShowInfo(roleid, 0));
				for (long toroleid : param.roleidlist) {
					if(myinfo.getRequesting().remove(toroleid) != null) {
                        result.roleidlist.add(toroleid);
                    }
				}
                Transaction.tsendWhileCommit(result.roleidlist, notify);
				return response(result);
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553907;

	public int getType() {
		return 6553907;
	}

	public java.util.ArrayList<Long> roleidlist;

	public CRejectFriend() {
		roleidlist = new java.util.ArrayList<Long>();
	}

	public CRejectFriend(java.util.ArrayList<Long> _roleidlist_) {
		this.roleidlist = _roleidlist_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(roleidlist.size());
		for (Long _v_ : roleidlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			roleidlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRejectFriend) {
			CRejectFriend _o_ = (CRejectFriend)_o1_;
			if (!roleidlist.equals(_o_.roleidlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roleidlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleidlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
