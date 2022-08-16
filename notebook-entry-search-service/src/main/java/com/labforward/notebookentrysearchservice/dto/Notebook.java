package com.labforward.notebookentrysearchservice.dto;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Notebook class for Notebook Table 
 * @author parmik
 *
 */

@Entity
@Table
public class Notebook {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String notebookName;
   
    // One notebook can have multiple notbook entries
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="notebookId")
    private Set<NotebookEntry> notebookEntries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

	public Set<NotebookEntry> getNotebookEntries() {
		return notebookEntries;
	}

	public void setNotebookEntries(Set<NotebookEntry> notebookEntries) {
		this.notebookEntries = notebookEntries;
	}
}