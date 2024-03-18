/**
 * This package contains classes related to database operations and transformations.
 * The classes in this package are:
 * <ul>
 *     <li>{@link lt.viko.ad.rdelinda.tinklai.DB.Client}: Represents the main entry point of the application.</li>
 *     <li>{@link lt.viko.ad.rdelinda.tinklai.DB.ClientSocket}: Represents a client socket for sending XML files over the network.</li>
 *     <li>{@link lt.viko.ad.rdelinda.tinklai.DB.HibernateUtil}: Provides utilities for managing Hibernate sessions and transactions.</li>
 *     <li>{@link lt.viko.ad.rdelinda.tinklai.DB.JAXBTransformer}: Handles JAXB transformation between POJOs and XML.</li>
 *     <li>{@link lt.viko.ad.rdelinda.tinklai.DB.Server}: Represents a server for receiving XML files over the network.</li>
 *     <li>{@link lt.viko.ad.rdelinda.tinklai.DB.TransformationManager}: Handles transformation between POJOs and XML using JAXB.</li>
 * </ul>
 * <p>
 * The {@code Client} class is the main entry point of the application, responsible for initiating database operations
 * and XML transformations. The {@code ClientSocket} class facilitates communication over the network by sending XML files.
 * The {@code HibernateUtil} class provides utilities for managing Hibernate sessions and transactions. The {@code JAXBTransformer}
 * class handles transformation between Java objects (POJOs) and XML using JAXB. The {@code Server} class represents a server
 * for receiving XML files over the network. The {@code TransformationManager} class encapsulates transformation logic between
 * POJOs and XML, using JAXB, and provides methods to transform to/from XML.
 * </p>
 */
package lt.viko.ad.rdelinda.tinklai.DB;
