package com.tsystems.bookstore.backing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tsystems.bookstore.ejb.service.BookreviewService;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Bookreview;
import com.tsystems.bookstore.persistence.entity.Usr;

@Named
@RequestScoped
public class BookreviewController {

	@Inject
	private BookreviewService bookreviewService;
	
	public String testSaveBookreview() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Create new Bookreview item");
		Bookreview dummyBookreview = generateDummyBookreview();
		bookreviewService.save(dummyBookreview);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testDeleteBookreview() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Delete Bookreview item");
		Bookreview bookreviewToDelete = generateDummyBookreview();
		bookreviewService.delete(bookreviewToDelete);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindAllBookreviews() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding book reviews");
		List<Bookreview> reviews = (ArrayList<Bookreview>) bookreviewService.findAll(Bookreview.class);
		printReviews(reviews);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindReviewsByBookId() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding book reviews by id");
		List<Bookreview> reviews = bookreviewService.findByBookId(1);
		printReviews(reviews);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindReviewsByUserId() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding book reviews by user id");
		List<Bookreview> reviews = bookreviewService.findByUserId(2);
		printReviews(reviews);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testGetTopRatedReviews() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Getting top rated reviews");
		List<Bookreview> reviews = bookreviewService.getTopRated(1, 20);
		printReviews(reviews);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testGetEditoralReviews() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Getting editoral reviews");
		List<Bookreview> reviews = bookreviewService.getEditoralReviews(3);
		printReviews(reviews);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testGetUserReviews() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Getting user reviews");
		List<Bookreview> reviews = bookreviewService.getUserReviews(2);
		printReviews(reviews);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	private static void printReviews(List<Bookreview> reviews) {
		for (Bookreview review : reviews) {
			System.out.println("Id: " + review.getId());
			System.out.println("Rating: " + review.getRating());
			System.out.println("Text: " + review.getText());
		}
	}
	
	private Bookreview generateDummyBookreview(){
		Bookreview bookreview = new Bookreview();
		bookreview.setId(999);
		Usr user = new Usr();
		user.setId(1);
		Book book = new Book();
		book.setId(2);
		bookreview.setUsr(user);
		bookreview.setBook(book);
		bookreview.setReviewType('R');
		bookreview.setPurchaseDate(new Date());
		bookreview.setReviewdate(new Date());
		bookreview.setPosition("Manager");
		bookreview.setJob("Cows Inc.");
		bookreview.setRating((short) 5);
		bookreview.setText("Great book.");
		return bookreview;
	}
}
