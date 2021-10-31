package jp.co.tk.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bbsthread")
public class BbsThreadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column
	private int id;
	@Column
	private int bbs_id;
	@Column
	private int user_id;
	@Column
	private String name;
	@Column
	private String contents;
	@Column
	private Timestamp add_date;
	@Column
	private Timestamp up_date;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(int bbs_id) {
		this.bbs_id = bbs_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Timestamp add_date) {
		this.add_date = add_date;
	}
	public Timestamp getUp_date() {
		return up_date;
	}
	public void setUp_date(Timestamp up_date) {
		this.up_date = up_date;
	}

}
