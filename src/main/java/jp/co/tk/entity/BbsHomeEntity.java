package jp.co.tk.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bbs")
public class BbsHomeEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column
	private int id;
	@Column
	private int user_id;
	@Column
	private String title;
	@Column
	private String name;
	@Column
	private String contents;
	@Column
	private LocalDateTime add_date;
	@Column
	private LocalDateTime up_date;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public LocalDateTime getAdd_date() {
		return add_date;
	}
	public void setAdd_date(LocalDateTime add_date) {
		this.add_date = add_date;
	}
	public LocalDateTime getUp_date() {
		return up_date;
	}
	public void setUp_date(LocalDateTime up_date) {
		this.up_date = up_date;
	}


}