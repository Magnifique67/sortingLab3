# SortingLab3 Project

SortingLab3 is a Java-based web application that implements various sorting algorithms and provides RESTful API endpoints following HATEOAS principles using the Spring Framework.

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven 3.x or higher

### Steps

1. **Clone the repository**

   ```bash
   git clone https://github.com/magnifique67/sortingLab3.git
   cd sortingLab3

2. **Build the project**
   ``mvn clean install
   ``
3.  **Run the application**
    ``mvn spring-boot:run
    ``
4. **Access the application**
   Open a web browser and go to `http://localhost:8080.`

### Usage
#### Sorting Algorithms
The application provides a web interface to demonstrate various sorting algorithms. Follow these steps to use the sorting functionality:
1. **Open the Sorting Algorithms Page**
   Open a web browser and go to` http://localhost:8080/sort`.
2. **Enter Data**

   - Enter data separated by commas in the "Enter data" input field.
   - Select one of the available sorting algorithms from the dropdown list.
3. **Sort**

   - Click the "Sort" button to submit the form. The application will sort the entered data using the selected algorithm.
4. **View Sorted Data**

   - After sorting, the sorted data will be displayed below the form.
### Example API Usage

- Get all books: `GET /api/books`

- Add a book: `POST /api/books/add`

- Update a book:` PUT /api/books/{id}`

- Delete a book: `DELETE /api/books/{id}`
### Loom video

### https://www.loom.com/share/6744963922354645bd3d367aa2087f09
