package edu.vanderbilt.imagecrawler;

import edu.vanderbilt.imagecrawler.crawlers.CrawlerType;
import edu.vanderbilt.imagecrawler.crawlers.ImageCrawler;
import edu.vanderbilt.imagecrawler.platform.Controller;
import edu.vanderbilt.imagecrawler.platform.JavaPlatform;
import edu.vanderbilt.imagecrawler.platform.Platform;
import edu.vanderbilt.imagecrawler.transforms.Transform;
import edu.vanderbilt.imagecrawler.utils.Options;
import edu.vanderbilt.imagecrawler.utils.UriUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        boolean local = false;
        String rootUrl = Options.DEFAULT_WEB_URL;
        if (local) {
            rootUrl = Platform.RESOURCES_URI_PREFIX + "/" + UriUtils.mapUriToRelativePath(Options.DEFAULT_WEB_URL);
        }
        Controller controller = Controller
                .newBuilder()
                .platform(new JavaPlatform())
                .localTransforms(true)
//                .transforms(Arrays.stream(Transform.Type.values()).collect(Collectors.toList()))
                .rootUrl(rootUrl)
                .localTransforms(true)
                .maxDepth(3)
                .diagnosticsEnabled(true)
                .build();

        ImageCrawler.Factory.newCrawler(CrawlerType.PROJECT_REACTOR, controller).run();
    }
}
