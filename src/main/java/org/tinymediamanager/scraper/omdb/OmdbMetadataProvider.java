/*
 * Copyright 2012 - 2017 Manuel Laggner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinymediamanager.scraper.omdb;

import java.util.List;

import org.tinymediamanager.scraper.MediaMetadata;
import org.tinymediamanager.scraper.MediaProviderInfo;
import org.tinymediamanager.scraper.MediaScrapeOptions;
import org.tinymediamanager.scraper.MediaSearchOptions;
import org.tinymediamanager.scraper.MediaSearchResult;
import org.tinymediamanager.scraper.UnsupportedMediaTypeException;
import org.tinymediamanager.scraper.mediaprovider.IMovieMetadataProvider;

/**
 * Central metadata provider class
 * 
 * @author Wolfgang Janes
 */
public class OmdbMetadataProvider implements IMovieMetadataProvider {
  static final MediaProviderInfo providerinfo = createMediaProviderInfo();
  private boolean                verbose      = false;

  private static MediaProviderInfo createMediaProviderInfo() {
    return new MediaProviderInfo("omdbapi", "omdbapi.com",
        "<html><h3>Omdbapi.com</h3><br />An other Movie Database<br /><br />Available languages: DE</html>");
  }

  @Override
  public MediaMetadata getMetadata(MediaScrapeOptions options) throws Exception {
    switch (options.getType()) {
      case MOVIE:
        return new OmdbMovieMetadataProvider(verbose).getMetadata(options);

      default:
        throw new UnsupportedMediaTypeException(options.getType());
    }
  }

  @Override
  public List<MediaSearchResult> search(MediaSearchOptions options) throws Exception {
    switch (options.getMediaType()) {
      case MOVIE:
        return new OmdbMovieMetadataProvider(verbose).search(options);

      default:
        throw new UnsupportedMediaTypeException(options.getMediaType());
    }

  }

  @Override
  public MediaProviderInfo getProviderInfo() {
    return providerinfo;
  }

  public void setVerbose(boolean verbose) {
    this.verbose = verbose;
  }

  public boolean getVerbose() {
    return this.verbose;
  }
}