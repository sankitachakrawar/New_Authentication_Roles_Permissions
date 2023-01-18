package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "userCount")
@Where(clause = "is_active = true")
@SQLDelete(sql = "UPDATE userCount SET is_active=false WHERE id=?")
public class UserCount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity id;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "is_active")
	private Boolean isActive = true;

	public Long getCountId() {
		return countId;
	}

	public void setCountId(Long countId) {
		this.countId = countId;
	}

	public UserEntity getId() {
		return id;
	}

	public void setId(UserEntity id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public UserCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCount(Long countId, UserEntity id, Date createdAt, Boolean isActive) {
		super();
		this.countId = countId;
		this.id = id;
		this.createdAt = createdAt;
		this.isActive = isActive;

	}

	@Override
	public String toString() {
		return "UserCount [countId=" + countId + ", id=" + id + ", createdAt=" + createdAt + ", isActive=" + isActive
				+ "]";
	}

}
