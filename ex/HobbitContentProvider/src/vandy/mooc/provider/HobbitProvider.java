package vandy.mooc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Content Provider used to store information about Hobbit characters.
 */
public class HobbitProvider extends ContentProvider {
    /**
     * Debugging tag used by the Android logger.
     */
    protected final static String TAG =
        HobbitProvider.class.getSimpleName();

    public enum ContentProviderType {
        HASH_MAP,
        SQLITE
    }

    private ContentProviderType mContentProviderType =
        ContentProviderType.SQLITE;
    // ContentProviderType.HASH_MAP;

    private HobbitProviderImpl mImpl;

    /**
     * Method called to handle type requests from client applications.
     * It returns the MIME type of the data associated with each URI.     */
    @Override
    public String getType(Uri uri) {
        return mImpl.getType(uri);
    }

    /**
     * Method called to handle insert requests from client
     * applications.
     */
    @Override
    public Uri insert(Uri uri,
                      ContentValues cvs) {
        return mImpl.insert(uri, cvs);
    }

    /**
     * Method that handles bulk insert requests.
     */
    @Override
    public int bulkInsert(Uri uri,
                          ContentValues[] cvsArray) {
        return mImpl.bulkInsert(uri, cvsArray);
    }

    /**
     * Method called to handle query requests from client
     * applications.
     */
    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        return mImpl.query(uri, 
                           projection,
                           selection,
                           selectionArgs,
                           sortOrder);
    }

    /**
     * Method called to handle update requests from client
     * applications.
     */
    @Override
    public int update(Uri uri,
                      ContentValues cvs,
                      String selection,
                      String[] selectionArgs) {
        return mImpl.update(uri,
                            cvs,
                            selection,
                            selectionArgs);
    }

    /**
     * Method called to handle delete requests from client
     * applications.
     */
    @Override
    public int delete(Uri uri,
                      String selection,
                      String[] selectionArgs) {
        return mImpl.delete(uri, 
                            selection,
                            selectionArgs);
    }

    /**
     * Return true if successfully started.
     */
    @Override
    public boolean onCreate() {
        switch(mContentProviderType) {
        case HASH_MAP:
            mImpl =
                new HobbitProviderHashMap(getContext());
            break;
        case SQLITE:
            mImpl = new HobbitProviderSQLite(getContext());
            break;
        }

        return mImpl.onCreate();
    }
}
