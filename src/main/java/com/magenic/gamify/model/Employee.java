package com.magenic.gamify.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="employee")
public class Employee implements Serializable, Comparable<Employee>{

	private Long id;
	
	private String name;
	private String username;
	@JsonIgnore
	private String password;
	private String jobClass;
	private String guild;
	private int level;
	private int experience;
	private Date joinDate;
	private String status;
	private String avatar;
	private String roles;
	
	private Set<Skill> skills;
	private Set<Trophy> trophies;
	private Set<Badge> badges;
	
	private Long numberOfBadges;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="class")
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	
	@Column(name="guild")
	public String getGuild() {
		return guild;
	}
	public void setGuild(String guild) {
		this.guild = guild;
	}
	
	@Column(name="level")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Column(name="experience")
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	@Column(name="join_date")
	@Temporal(TemporalType.DATE)
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="avatar")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name="role")
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<Skill> getSkills() {
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		if(this.skills != null) {
			this.skills.clear();
			this.skills.addAll(skills);
		}
		else {
			this.skills = skills;
		}
	}
	
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<Trophy> getTrophies() {
		return trophies;
	}
	public void setTrophies(Set<Trophy> trophies) {
		if(this.trophies != null) {
			this.trophies.clear();
			this.trophies.addAll(trophies);
		}
		else {
			this.trophies = trophies;
		}
	}
	
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<Badge> getBadges() {
		return badges;
	}
	
	public void setBadges(Set<Badge> badges) {
		if(this.badges != null) {
			this.badges.clear();
			this.badges.addAll(badges);
		}
		else {
			this.badges = badges;
		}
	}
	
	@Transient
	public Long getNumberOfBadges() {
		return numberOfBadges;
	}
	public void setNumberOfBadges(Long numberOfBadges) {
		this.numberOfBadges = numberOfBadges;
	}
	public String toString() {
		return "id = " + id + ", name = " + name + ", username = " + username + ", class = " + jobClass + ", guild = " + guild
				+ ", level = " + level + ", xp = " + experience + ", status = " + status;
	}
	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return o.numberOfBadges.compareTo(this.numberOfBadges);
	}
	
}
