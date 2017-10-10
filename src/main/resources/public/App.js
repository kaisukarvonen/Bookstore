import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';

class App extends React.Component {
		  constructor(props) {
		      super(props);
		      this.state = {
		          books: ['plaa'],
		      };
          this.deleteBook = this.deleteBook.bind(this);
          this.createBook = this.createBook.bind(this);
		   }

		  componentDidMount() {
			// Call load books
      this.loadBooksFromServer();
		  }

		  // Load books from database
		  loadBooksFromServer() {
		      fetch('/books')
          .then(result => result.json())
            .then(result => this.setState({ books:result })
          )
		  }

      deleteBook(book) {
        fetch('/delete/'+book.id);
        this.loadBooksFromServer();
      }

      createBook(book) {
        //fetch
        fetch('/add', {
          method:'POST',
          entity:book
        });
        this.loadBooksFromServer();
      }

		  render() {
		    return (
		       <div>
              <BookForm createBook={this.createBook} />
              <BookTable
                deleteBook={this.deleteBook}
                books={this.state.books} />

		       </div>
		    );
		  }
		}

	class BookTable extends React.Component {
		constructor(props) {
			super(props);
		}

		render() {
      const { books } = this.props;
      const allBooks = books.map(book =>
        <Book
          key={book.id}
          book={book}
          deleteBook={this.props.deleteBook}
        />
      );
	    	return (
          <table className="table table-striped">
          <tr>
          <th>Title</th>
          <th>Author</th>
          <th>Year</th>
          <th>ISBN</th>
          <th>Price</th>
          </tr>
          {allBooks}
          </table>
	     	);
	  }
	}

	class Book extends React.Component {
	    constructor(props) {
	        super(props);
          this.deleteBook = this.deleteBook.bind(this);
	    }

      deleteBook() {
        this.props.deleteBook(this.props.book);
        console.log(this.props.book.id);
      }

	    render() {
	        return (

            <tr>
            <td>{this.props.book.title}</td>
            <td>{this.props.book.author}</td>
            <td>{this.props.book.year}</td>
            <td>{this.props.book.isbn}</td>
            <td>{this.props.book.price}</td>
            <td className="btn btn-danger" onClick={this.deleteBook}>Delete</td>
            </tr>

	        );
	    }
	}


  class BookForm extends React.Component {
      constructor(props) {
          super(props);
          this.state = {title: '', author: '', year: 2017,
          isbn: '', price: 0 };
          this.handleSubmit = this.handleSubmit.bind(this);
          this.handleChange = this.handleChange.bind(this);
      }

      handleChange(event) {
         // Set states here
         const name = event.target.name;
         this.setState({ [name]: event.target.value });
      }

      handleSubmit(event) {
          event.preventDefault();
          // Create new srudent object and call createStudent
          const newBook = {title:this.state.title, author:this.state.author,
          year:this.state.year, isbn:this.state.isbn, price:this.state.price};
          console.log(newBook);
          this.props.createBook(newBook);
      }

      render() {
          return (
              <div className="panel panel-default">
                  <div className="panel-heading">Create student</div>
                  <div className="panel-body">
                  <form className="form-inline">
                      <div className="col-md-2">
                          <input type="text" placeholder="Title" className="form-control"  name="title" onChange={this.handleChange}/>
                      </div>
                      <div className="col-md-2">
                          <input type="text" placeholder="Author" className="form-control" name="author" onChange={this.handleChange}/>
                      </div>
                      <div className="col-md-2">
                          <input type="text" placeholder="Year" className="form-control" name="year" onChange={this.handleChange}/>
                      </div>
                      <div className="col-md-2">
                          <input type="text" placeholder="ISBN" className="form-control" name="isbn" onChange={this.handleChange}/>
                      </div>
                      <div className="col-md-2">
                          <input type="number" placeholder="Price" className="form-control" step="0.1" name="price" onChange={this.handleChange}/>
                      </div>
                      <div className="col-md-2">
                          <button className="btn btn-success" onClick={this.handleSubmit.bind(this)}>Save</button>
                      </div>
                  </form>
                  </div>
              </div>
          );
      }
  }

	ReactDOM.render(<App />, document.getElementById('root') );
