# Filelize for java/kotlin

Filelize is a lightweight Java/Kotlin library designed to simplify the process of saving objects as JSON in human-readable files.

### Usange

To integrate Filelize into your project, you have two options:

1. **Using Maven:** Add the following dependency to your pom.xml file and follow the guide for [Working with the Apache Maven registry](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)
````xml
<dependency>
  <groupId>org.filelize</groupId>
  <artifactId>filelize-java</artifactId>
  <version>1.0.0</version>
</dependency>
````
2. **Manual Build:** Alternatively, you can download this repository and build it locally.

### Saving to a single file
To save an object to a single file, annotate your model class with `@Filelize` and set the `type` parameter to `FilelizeType.SINGLE_FILE`. Additionally, mark the identifying attribute with `@Id`. 
````java
import org.filelize.Filelize;
import org.filelize.Id;

@Filelize(name = "my_something", type = FilelizeType.SINGLE_FILE)
public class Something {
    @Id
    private String id;
    private String name;
    ...
}
````

### Saving to multiple files
For saving objects to multiple files, follow the same steps as for single-file saving, but set the type parameter to `FilelizeType.MULTIPLE_FILES`.
````java
import org.filelize.Filelize;
import org.filelize.Id;

@Filelize(name = "my_something", type = FilelizeType.MULTIPLE_FILES)
public class Something {
    @Id
    private String id;
    private String name;
    ...
}
````

### Find a file
````java
var something = filelizer.find("id1", Something.class);
````

### FindAll:
````java
var somethings = filelizer.findAll(Something.class);
````

### Save a file
````java
var id = filelizer.save(something);
````

### SaveAll in one or multiple file(s):
````java
var ids = filelizer.saveAll(somethings);
````

## Contribute
Contributions are welcomed! Feel free to create a pull request to contribute.