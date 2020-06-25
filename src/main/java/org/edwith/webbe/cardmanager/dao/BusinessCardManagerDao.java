package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.db.DBUtils;
import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	public List<BusinessCard> searchBusinessCard(String keyword){
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
    	
    	List<BusinessCard> bsList = new ArrayList<BusinessCard>();
    	
    	try {
			conn = DBUtils.getConnetion();
			String sql = "select name, phone, companyname, createdate from businesscard where name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%" + keyword + "%");
			rs = ps.executeQuery();
			System.out.println(1);
			while (rs.next()) {
				System.out.println(2);
				BusinessCard bc = new BusinessCard();
				bc.setName(rs.getString(1));
				bc.setPhone(rs.getString(2));
				bc.setCompanyName(rs.getString(3));
				bc.setCreateDate(formatType.parse(rs.getString(4)));
				bsList.add(bc);
			}

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, ps, rs);
		}
    	return bsList;
    }

	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		Connection conn = null;
		PreparedStatement ps = null;
		SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");

		// 구현하세요.
		try {
			conn = DBUtils.getConnetion();
			String sql = "insert into businesscard(name, phone, companyname, createdate) values(?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());
			ps.setString(4, formatType.format(businessCard.getCreateDate()));

			if (ps.execute()) {
				System.out.println(businessCard.toString());
				System.out.println("명함이 성공적으로 입력되었습니다!");
				return businessCard;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, ps);
		}
		return null;
	}
}
