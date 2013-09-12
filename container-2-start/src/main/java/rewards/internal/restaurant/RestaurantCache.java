package rewards.internal.restaurant;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.CacheEntryFactory;
import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;
import net.sf.ehcache.writer.CacheWriter;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 11/09/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestaurantCache {

    /**
     * cache-as-sor   http://ehcache.org/documentation/get-started/getting-started#cache-as-sor
     *
     * The cache-as-sor pattern implies using the cache as though it were the primary system-of-record (SOR). The pattern delegates SOR reading and writing activies to the cache, so that application code is absolved of this responsibility.

     To implement the cache-as-sor pattern, use a combination of the following read and write patterns:

     read-through
     write-through or write-behind

     read-through

     The read-through pattern mimics the structure of the cache-aside pattern when reading data. The difference is that you must implement the CacheEntryFactory interface to instruct the cache how to read objects on a cache miss, and you must wrap the Ehcache instance with an instance of SelfPopulatingCache. Compare the appearance of the read-through pattern code to the code provided in the cache-aside pattern. (The full example is provided at the end of this document that includes a read-through and write-through implementation).

     write-through

     The write-through pattern mimics the structure of the cache-aside pattern when writing data. The difference is that you must implement the CacheWriter interface and configure the cache for write-through or write-behind. A write-through cache writes data to the system-of-record in the same thread of execution, therefore in the common scenario of using a database transaction in context of the thread, the write to the database is covered by the transaction in scope. More details (including configuration settings) can be found in the User Guide chapter on Write-through and Write-behind Caching.

     write-behind

     The write-behind pattern changes the timing of the write to the system-of-record. Rather than writing to the System of Record in the same thread of execution, write-behind queues the data for write at a later time.

     The consequences of the change from write-through to write-behind are that the data write using write-behind will occur outside of the scope of the transaction.

     This often-times means that a new transaction must be created to commit the data to the system-of-record that is separate from the main transaction. More details (including configuration settings) can be found in the User Guide chapter on Write-through and Write-behind Caching.
     *//*

     private final Ehcache cache;

    public RestaurantCache(Ehcache cache){
        cache.registerCacheWriter(new RestaurantCacheWriter());
        this.cache = new SelfPopulatingCache(cache, new RestaurantEntryFactory());
    }

    private class RestaurantCacheWriter implements CacheWriter{
        public CacheWriter clone(Ehcache cache) throws CloneNotSupportedException{
            throw new CloneNotSupportedException();
        }

        public void init(){}
        public void dispose() throws CacheException{}
        public void write(Element element) throws CacheException{
            writeDataToDataStore(element.getObjectKey(), element.getObjectValue());
        }
        public void writeAll(Collection<Element> elements) throws CacheException{
            for (Element element: elements){
                write(element);
            }
        }
        public void delete(CacheEntry entry) throws CacheException{
            deleteDataFromDataStore(entry.getKey());
        }

        public void deleteAll(Collection<Element> elements) throws CacheException{
            for(Element element : elements) {
                delete(element);
            }
        }

    }

    private class RestaurantEntryFactory implements CacheEntryFactory{
        public Object createEntry(Object key) throws Exception
        {
            return readDataFromDataSource(key);
        }
    }*/

}
