package com.tsystems.bookstore.backing;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tsystems.bookstore.ejb.service.AuthorService;
import com.tsystems.bookstore.persistence.entity.Author;

@Named
// Determine Scope for Managed Bean from javax.enterprise.context package
@RequestScoped
public class AuthorController {

	@Inject
	private AuthorService authorService;

	public String testCreateAuthor() {
		System.out
				.println("----------------------------------------------------------");
		System.out.println("Create new Author");
		Author dummyAuthor = generateDummyAuthor();
		authorService.createOrUpdateAuthor(dummyAuthor);
		System.out
				.println("----------------------------------------------------------");
		return "";
	}

	public String testDeleteAuthor() {
		System.out
				.println("----------------------------------------------------------");
		System.out.println("Delete Author");
		Author authorToDelete = generateDummyAuthor();
		authorService.deleteAuthorById(authorToDelete.getId());
		System.out
				.println("----------------------------------------------------------");
		return "";
	}

	public String testFindAuthorByFirstname() {
		System.out
				.println("----------------------------------------------------------");
		System.out.println("Start Finding author by the firstname field ");
		Author newAuthor = authorService.getAuthorByFirstname("");
		System.out
				.println(newAuthor.getId() + " " + newAuthor.getFirstname()
						+ " " + newAuthor.getLastname() + " "
						+ newAuthor.getBirthday());
		System.out
				.println("----------------------------------------------------------");
		return "";

	}

	public String testChangeAuthorLastname() {
		System.out
				.println("----------------------------------------------------------");
		System.out.println("Start Changing author lastname field ");
		Author newAuthor = generateDummyAuthor();
		authorService.changeAuthorLastname(newAuthor, "NEW lastname");
		System.out
				.println(newAuthor.getId() + " " + newAuthor.getFirstname()
						+ " " + newAuthor.getLastname() + " "
						+ newAuthor.getBirthday());
		System.out
				.println("----------------------------------------------------------");
		return "";
	}

	private Author generateDummyAuthor() {

		String firstname = "Matthew";
		String lastname = "MacDonald";
		String str = "1978-01-12";

		Date birthday = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthday = formatter.parse(str);
		} catch (Exception e) {
			System.out.println("Format exception...");
		}

		Author author = new Author();
		author.setId(333);
		author.setFirstname(firstname);
		author.setLastname(lastname);
		author.setBirthday(birthday);

		return author;
	}

}
